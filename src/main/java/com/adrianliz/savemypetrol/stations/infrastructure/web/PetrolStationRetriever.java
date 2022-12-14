package com.adrianliz.savemypetrol.stations.infrastructure.web;

import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.infrastructure.repository.PetrolStationConverter;
import com.adrianliz.savemypetrol.stations.infrastructure.repository.record.MongoDataAccessor;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
@Slf4j
public final class PetrolStationRetriever {
  private final WebClient client;
  private final MongoDataAccessor dataAccessor;

  private Flux<PetrolStation> fetchPetrolStations() {
    return client
        .get()
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<PetrolStationsResponse>() {})
        .map(PetrolStationsResponse::getStations)
        .flatMapIterable(
            petrolStations ->
                petrolStations.stream()
                    .map(PetrolStationResponse::mapToPetrolStation)
                    .collect(Collectors.toList()));
  }

  // NOTE: Every 30 min
  @Scheduled(fixedDelay = 1800000)
  public void persistPetrolStations() {
    log.info("PetrolStationRetriever> Starting at {}", LocalDateTime.now());
    final var startTime = Instant.now();

    dataAccessor
        .saveAll(fetchPetrolStations().map(PetrolStationConverter::toRecord))
        .doOnTerminate(
            () -> {
              final var endTime = Instant.now();
              final long secondsRunning = Duration.between(startTime, endTime).getSeconds();

              log.info(
                  "PetrolStationRetriever> Finished at {} ({} seconds running)",
                  LocalDateTime.now(),
                  secondsRunning);
            })
        .doOnError(
            (ex) ->
                log.error(
                    "PetrolStationRetriever> Error persisting petrol stations at {} ({})",
                    LocalDateTime.now(),
                    ex.getMessage()))
        .subscribe();
  }
}
