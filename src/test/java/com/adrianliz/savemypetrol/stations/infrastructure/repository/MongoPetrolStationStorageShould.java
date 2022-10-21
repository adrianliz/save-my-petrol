package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.adrianliz.savemypetrol.app.config.CacheConfiguration;
import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import com.adrianliz.savemypetrol.common.domain.Page;
import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationLocationMother;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationMother;
import com.adrianliz.savemypetrol.stations.infrastructure.config.PetrolStationsCacheConfiguration;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
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

  private Flux<PetrolStation> givenThereIsRandomPetrolStations() {
    final var petrolStations = PetrolStationMother.randoms();
    return Flux.concat(petrolStations.stream().map(storage::save).collect(Collectors.toList()));
  }

  private Flux<PetrolStation> givenThereIsRandomPetrolStationsLocatedBetween(
      final PetrolStationLocation sourceLocation, final PetrolStationLocation targetLocation) {

    final var petrolStations =
        PetrolStationMother.randomsWithLocation(
            PetrolStationLocationMother.randomWithLocation(
                LocationValueObject.between(sourceLocation, targetLocation)));
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

  @Test
  void find_near_petrol_stations() {
    final var sourceLocation = PetrolStationLocationMother.random();
    final var targetLocation = PetrolStationLocationMother.random();
    final var distanceBetweenStations = sourceLocation.distanceTo(targetLocation);

    final var filter =
        new PetrolStationFilter.PetrolStationFilterBuilder()
            .sourceLocation(sourceLocation)
            .maxMetersFromSource(distanceBetweenStations)
            .pageRequested(Page.defaultPage())
            .build();

    final var expectedPetrolStations =
        givenThereIsRandomPetrolStationsLocatedBetween(sourceLocation, targetLocation);
    final var foundPetrolStations = storage.find(filter);

    StepVerifier.create(expectedPetrolStations.zipWith(foundPetrolStations, PetrolStation::equals))
        .thenConsumeWhile((b) -> b.equals(true))
        .verifyComplete();
  }
}
