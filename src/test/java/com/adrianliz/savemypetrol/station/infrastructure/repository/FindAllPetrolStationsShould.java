package com.adrianliz.savemypetrol.station.infrastructure.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.Test;

public final class FindAllPetrolStationsShould extends MongoPetrolStationStorageTestCase {

  @Test
  void find_all() {
    final var expectedPetrolStations = givenThereIsRandomPetrolStations();
    final var foundPetrolStations = storage.findAll();

    assertThat(
            Objects.requireNonNull(expectedPetrolStations.collectList().block())
                .containsAll(Objects.requireNonNull(foundPetrolStations.collectList().block())))
        .isTrue();
  }
}
