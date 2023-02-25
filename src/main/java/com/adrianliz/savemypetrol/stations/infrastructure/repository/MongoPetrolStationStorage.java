package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import com.adrianliz.savemypetrol.stations.infrastructure.repository.record.PetrolStationRecord;
import com.hazelcast.map.IMap;
import com.mongodb.client.result.DeleteResult;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoPetrolStationStorage implements PetrolStationsRepository {
  private final ReactiveMongoTemplate dataAccessor;
  private final IMap<PetrolStationId, PetrolStation> petrolStationsCache;

  @Override
  public Flux<PetrolStation> findAll() {
    return dataAccessor
        .findAll(PetrolStationRecord.class)
        .map(PetrolStationConverter::toEntity)
        .doOnNext(petrolStation -> petrolStationsCache.setAsync(petrolStation.id(), petrolStation));
  }

  @Override
  public Mono<PetrolStation> findById(final PetrolStationId id) {
    return Mono.fromCompletionStage(() -> petrolStationsCache.getAsync(id))
        .switchIfEmpty(
            dataAccessor
                .findById(id.value(), PetrolStationRecord.class)
                .map(PetrolStationConverter::toEntity))
        .doOnNext(petrolStation -> petrolStationsCache.setAsync(petrolStation.id(), petrolStation));
  }

  @Override
  public Flux<PetrolStation> find(final PetrolStationFilter filter) {
    return dataAccessor
        .geoNear(MongoPetrolStationFilter.from(filter), PetrolStationRecord.class)
        .sort(Comparator.comparing(GeoResult::getDistance))
        .map(GeoResult::getContent)
        .map(PetrolStationConverter::toEntity)
        .doOnNext(petrolStation -> petrolStationsCache.setAsync(petrolStation.id(), petrolStation));
  }

  Mono<PetrolStation> save(final PetrolStation petrolStation) {
    return dataAccessor
        .save(PetrolStationConverter.toRecord(petrolStation))
        .map(PetrolStationConverter::toEntity);
  }

  Mono<Long> clear() {
    return dataAccessor.remove(PetrolStationRecord.class).all().map(DeleteResult::getDeletedCount);
  }
}
