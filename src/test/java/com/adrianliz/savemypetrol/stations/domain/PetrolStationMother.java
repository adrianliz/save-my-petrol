package com.adrianliz.savemypetrol.stations.domain;

import java.util.Collections;
import java.util.List;

public final class PetrolStationMother {
  public static PetrolStation create(
      final PetrolStationId id,
      final PetrolStationName name,
      final PetrolStationLocation location,
      final List<PetrolStationProduct> products) {

    return new PetrolStation(id, name, location, products);
  }

  public static PetrolStation randomWithoutProducts() {
    return create(
        PetrolStationIdMother.random(),
        PetrolStationNameMother.random(),
        PetrolStationLocationMother.random(),
        Collections.emptyList());
  }
}