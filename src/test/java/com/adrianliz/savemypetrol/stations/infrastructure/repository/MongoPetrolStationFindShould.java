package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.adrianliz.savemypetrol.common.domain.DoubleMother;
import com.adrianliz.savemypetrol.common.domain.Page;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationLocationMother;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public final class MongoPetrolStationFindShould extends MongoInfrastructureTestCase {

  @Test
  void find_near_petrol_stations() {
    final var sourceLocation = PetrolStationLocationMother.random();
    final var targetLocation =
        PetrolStationLocationMother.move(
            sourceLocation, DoubleMother.randomBetween(10000L, 100000L));
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

    assertThat(
            Objects.requireNonNull(expectedPetrolStations.collectList().block())
                .containsAll(Objects.requireNonNull(foundPetrolStations.collectList().block())))
        .isTrue();
  }
}