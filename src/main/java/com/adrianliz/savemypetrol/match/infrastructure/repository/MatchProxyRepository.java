package com.adrianliz.savemypetrol.match.infrastructure.repository;

import com.adrianliz.savemypetrol.match.domain.FindMatchesProcess;
import com.adrianliz.savemypetrol.match.domain.Match;
import com.adrianliz.savemypetrol.match.domain.MatchRepository;
import com.adrianliz.savemypetrol.match.infrastructure.repository.record.MatchRecord;
import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationRepository;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerRecord;
import com.hazelcast.map.IMap;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@AllArgsConstructor
@Slf4j
public class MatchProxyRepository implements MatchRepository {

  private final ReactiveMongoTemplate dataAccessor;
  private final PetrolStationRepository petrolStationRepository;
  private final IMap<FindMatchesProcess, List<Match>> matchesCache;

  @Override
  public Mono<FindMatchesProcess> findAll() {
    final var process = FindMatchesProcess.create();
    final var startTime = Instant.now();
    log.info("MatchProxyRepository> Finding matches started at {}", LocalDateTime.now());

    Flux.defer(() -> petrolStationRepository.findAll().flatMap(this::findMatchesFrom))
        .subscribeOn(Schedulers.boundedElastic())
        .collectList()
        .doOnNext(matches -> matchesCache.put(process, matches))
        .doOnTerminate(
            () -> {
              final var endTime = Instant.now();
              final long secondsRunning = Duration.between(startTime, endTime).getSeconds();

              log.info(
                  "MatchProxyRepository> Finding matches finished at {} ({} seconds running)",
                  LocalDateTime.now(),
                  secondsRunning);
            })
        .subscribe();
    return Mono.just(process);
  }

  @Override
  public Flux<Match> resolve(final FindMatchesProcess process) {
    return Flux.fromIterable(
        Optional.ofNullable(matchesCache.remove(process)).orElse(Collections.emptyList()));
  }

  Flux<Match> findMatchesFrom(final PetrolStation petrolStation) {
    final var petrolStationProducts = petrolStation.products();
    if (petrolStationProducts.isEmpty()) {
      return Flux.empty();
    }
    final var petrolStationLocation = petrolStation.location();

    final var targetProducts =
        new Criteria()
            .orOperator(
                petrolStationProducts.stream()
                    .map(
                        petrolStationProduct ->
                            Criteria.where("targetProduct.typeId")
                                .is(petrolStationProduct.type().id())
                                .and("targetProduct.cents")
                                .gte(petrolStationProduct.price().cents()))
                    .toList());

    final var matches =
        Aggregation.newAggregation(
            Aggregation.geoNear(
                NearQuery.near(petrolStationLocation.longitude(), petrolStationLocation.latitude())
                    .spherical(true)
                    .distanceMultiplier(Metrics.KILOMETERS.getMultiplier() * 1000),
                "metersFromSource"),
            Aggregation.project(
                    "id", "userId", "metersFromSource", "maxMetersFromSource", "targetProduct")
                .andExpression("maxMetersFromSource - metersFromSource")
                .as("deltaMetersFromSource"),
            Aggregation.match(Criteria.where("deltaMetersFromSource").gte(0)),
            Aggregation.match(targetProducts));

    return dataAccessor
        .aggregate(matches, TriggerRecord.class, MatchRecord.class)
        .map(matchResult -> matchResult.buildSubscriptionMatch(petrolStation));
  }
}
