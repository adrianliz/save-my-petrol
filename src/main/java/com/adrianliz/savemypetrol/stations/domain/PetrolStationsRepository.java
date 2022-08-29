package com.adrianliz.savemypetrol.stations.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetrolStationsRepository {
  Flux<PetrolStation> findAll();

  Mono<PetrolStation> findById(final PetrolStationId id);

  Flux<PetrolStation> find(final PetrolStationsFilter filter);
}
