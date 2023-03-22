package com.adrianliz.savemypetrol.station.infrastructure.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.adrianliz.savemypetrol.common.domain.DoubleMother;
import com.adrianliz.savemypetrol.station.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocationMother;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public final class FindPetrolStationsShould extends MongoPetrolStationStorageTestCase {

  @Test
  void find_near_petrol_stations() {
    final var sourceLocation = PetrolStationLocationMother.random();
    final var targetLocation =
        PetrolStationLocationMother.move(sourceLocation, DoubleMother.randomBetween(5000L, 25000L));
    final var distanceBetweenStations = sourceLocation.metersTo(targetLocation);

    final var filter =
        new PetrolStationFilter.PetrolStationFilterBuilder()
            .sourceLocation(sourceLocation)
            .maxMetersFromSource(distanceBetweenStations)
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
