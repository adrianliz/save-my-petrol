package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.adrianliz.savemypetrol.app.config.CacheConfiguration;
import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationLocationMother;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationMother;
import com.adrianliz.savemypetrol.stations.infrastructure.config.PetrolStationsCacheConfiguration;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest(
    classes = {
      CacheConfiguration.class,
      PetrolStationsCacheConfiguration.class,
      MongoTestConfiguration.class,
      MongoPetrolStationStorage.class
    })
@Slf4j
public class MongoInfrastructureTestCase {
  @Autowired protected MongoPetrolStationStorage storage;

  @AfterEach
  void tearDown() {
    storage
        .clear()
        .doOnNext(
            documentsDeleted -> log.info("{} documents deleted in tearDown", documentsDeleted))
        .block();
  }

  protected Flux<PetrolStation> givenThereIsRandomPetrolStations() {
    final var petrolStations = PetrolStationMother.randoms();
    return Flux.concat(petrolStations.stream().map(storage::save).collect(Collectors.toList()));
  }

  protected Flux<PetrolStation> givenThereIsRandomPetrolStationsLocatedBetween(
      final PetrolStationLocation sourceLocation, final PetrolStationLocation targetLocation) {

    final var petrolStations =
        PetrolStationMother.randomsWithLocation(
            PetrolStationLocationMother.randomWithLocation(
                LocationValueObject.between(sourceLocation, targetLocation)));
    return Flux.concat(petrolStations.stream().map(storage::save).collect(Collectors.toList()));
  }
}
