package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import com.adrianliz.savemypetrol.stations.infrastructure.web.PetrolStationRetriever;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoPetrolStationStorage implements PetrolStationsRepository {
  private final PetrolStationRetriever retriever;

  @Override
  public Flux<PetrolStation> findAll() {
    return retriever.findAll();
  }

  @Override
  public Mono<PetrolStation> findById(final PetrolStationId id) {
    return retriever.findById(id);
  }

  @Override
  public Flux<PetrolStation> find(final PetrolStationFilter filter) {
    return filter.applyTo(retriever.findAll());
  }
}
