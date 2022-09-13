package com.adrianliz.savemypetrol.stations.infrastructure;

import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import com.hazelcast.map.IMap;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class PetrolStationsWebClientRepository implements PetrolStationsRepository {

  private final WebClient client;
  private final IMap<PetrolStationId, PetrolStation> petrolStationsCache;

  @Override
  public Flux<PetrolStation> findAll() {
    return petrolStationsCache.isEmpty()
        ? client
            .get()
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<PetrolStationsResponse>() {})
            .map(PetrolStationsResponse::getStations)
            .flatMapIterable(
                petrolStations ->
                    petrolStations.stream()
                        .map(PetrolStationResponse::mapToPetrolStation)
                        .collect(Collectors.toList()))
            .doOnNext(
                petrolStation -> petrolStationsCache.setAsync(petrolStation.id(), petrolStation))
        : Flux.fromIterable(petrolStationsCache.values());
  }

  @Override
  public Mono<PetrolStation> findById(final PetrolStationId id) {
    return Mono.fromCompletionStage(() -> petrolStationsCache.getAsync(id))
        .switchIfEmpty(findAll().filter(petrolStation -> petrolStation.hasId(id)).next())
        .doOnNext(petrolStation -> petrolStationsCache.setAsync(petrolStation.id(), petrolStation));
  }

  @Override
  public Flux<PetrolStation> find(final PetrolStationFilter filter) {
    return petrolStationsCache.isEmpty()
        ? filter.applyTo(findAll())
        : filter.applyTo(Flux.fromIterable(petrolStationsCache.values()));
  }
}
