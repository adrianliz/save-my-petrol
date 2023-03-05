package com.adrianliz.savemypetrol.station.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetrolStationRepository {

  Flux<PetrolStation> findAll();

  Mono<PetrolStation> findById(final PetrolStationId id);

  Flux<PetrolStation> find(final PetrolStationFilter filter);
}
