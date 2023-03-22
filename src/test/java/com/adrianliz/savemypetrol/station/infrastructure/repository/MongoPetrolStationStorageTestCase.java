package com.adrianliz.savemypetrol.station.infrastructure.repository;

import com.adrianliz.savemypetrol.app.config.CacheConfiguration;
import com.adrianliz.savemypetrol.common.infrastructure.MongoTestConfiguration;
import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocationMother;
import com.adrianliz.savemypetrol.station.domain.PetrolStationMother;
import com.adrianliz.savemypetrol.station.infrastructure.config.PetrolStationsCacheConfiguration;
import com.adrianliz.savemypetrol.station.infrastructure.repository.record.PetrolStationRecord;
import com.hazelcast.map.IMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import reactor.core.publisher.Flux;

@Profile("test")
@SpringBootTest(
    classes = {
      CacheConfiguration.class,
      PetrolStationsCacheConfiguration.class,
      MongoTestConfiguration.class,
      MongoPetrolStationStorage.class
    })
@Slf4j
public class MongoPetrolStationStorageTestCase {

  @Autowired private ReactiveMongoTemplate dataAccessor;
  @Autowired private IMap<PetrolStationId, PetrolStation> petrolStationsCache;

  @Autowired protected MongoPetrolStationStorage storage;

  @BeforeEach
  void setUp() {
    dataAccessor
        .indexOps(PetrolStationRecord.class)
        .ensureIndex(new GeospatialIndex("location").typed(GeoSpatialIndexType.GEO_2DSPHERE))
        .block();
  }

  @AfterEach
  void tearDown() {
    petrolStationsCache.clear();
    storage
        .clear()
        .doOnNext(
            documentsDeleted -> log.info("{} documents deleted in tearDown", documentsDeleted))
        .block();
  }

  Flux<PetrolStation> givenThereIsRandomPetrolStations() {
    final var petrolStations = PetrolStationMother.randoms();
    return Flux.concat(petrolStations.stream().map(storage::save).collect(Collectors.toList()));
  }

  Flux<PetrolStation> givenThereIsRandomPetrolStationsLocatedBetween(
      final PetrolStationLocation sourceLocation, final PetrolStationLocation targetLocation) {

    final var petrolStations =
        PetrolStationMother.randomsWithLocation(
            PetrolStationLocationMother.randomWithLocation(
                sourceLocation.randomUntil(targetLocation)));
    return Flux.concat(petrolStations.stream().map(storage::save).collect(Collectors.toList()));
  }
}
