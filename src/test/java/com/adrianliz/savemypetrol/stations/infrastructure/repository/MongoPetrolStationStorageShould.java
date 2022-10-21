package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.adrianliz.savemypetrol.app.config.CacheConfiguration;
import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationMother;
import com.adrianliz.savemypetrol.stations.infrastructure.config.PetrolStationsCacheConfiguration;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(
    classes = {
      CacheConfiguration.class,
      PetrolStationsCacheConfiguration.class,
      MongoTestConfiguration.class,
      MongoPetrolStationStorage.class
    })
public final class MongoPetrolStationStorageShould {

  @Autowired private MongoPetrolStationStorage storage;

  private Mono<PetrolStation> givenThereIsAPetrolStation(final PetrolStation petrolStation) {
    return storage.save(petrolStation);
  }

  private Flux<PetrolStation> givenThereIsRandomPetrolStations() {
    final var petrolStations = PetrolStationMother.randoms();
    return Flux.concat(petrolStations.stream().map(storage::save).collect(Collectors.toList()));
  }

  @Test
  void find_all_petrol_stations() {
    final var expectedPetrolStations = givenThereIsRandomPetrolStations();
    final var foundPetrolStations = storage.findAll();

    StepVerifier.create(expectedPetrolStations.zipWith(foundPetrolStations, PetrolStation::equals))
        .thenConsumeWhile((b) -> b.equals(true))
        .verifyComplete();
  }

  void find_near_petrol_stations() {}
}
