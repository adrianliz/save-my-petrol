package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.ListMother;
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

  private static PetrolStation randomWithLocation(final PetrolStationLocation location) {
    return create(
        PetrolStationIdMother.random(),
        PetrolStationNameMother.random(),
        location,
        PetrolStationProductMother.randoms());
  }

  public static PetrolStation random() {
    return create(
        PetrolStationIdMother.random(),
        PetrolStationNameMother.random(),
        PetrolStationLocationMother.random(),
        PetrolStationProductMother.randoms());
  }

  public static List<PetrolStation> randomsWithLocation(final PetrolStationLocation location) {
    return ListMother.random(() -> randomWithLocation(location));
  }

  public static List<PetrolStation> randoms() {
    return ListMother.random(PetrolStationMother::random);
  }
}
