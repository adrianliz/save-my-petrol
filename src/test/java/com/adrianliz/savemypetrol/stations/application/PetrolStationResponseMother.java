package com.adrianliz.savemypetrol.stations.application;

import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationMother;

public final class PetrolStationResponseMother {
  public static PetrolStationResponse create(final PetrolStation petrolStation) {
    return PetrolStationResponse.from(petrolStation);
  }

  public static PetrolStationResponse randomWithoutProducts() {
    return create(PetrolStationMother.randomWithoutProducts());
  }
}
