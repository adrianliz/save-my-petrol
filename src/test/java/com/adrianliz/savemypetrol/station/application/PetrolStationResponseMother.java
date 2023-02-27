package com.adrianliz.savemypetrol.station.application;

import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationMother;

public final class PetrolStationResponseMother {
  public static PetrolStationResponse create(final PetrolStation petrolStation) {
    return PetrolStationResponse.from(petrolStation);
  }

  public static PetrolStationResponse randomWithoutProducts() {
    return create(PetrolStationMother.randomWithoutProducts());
  }
}
