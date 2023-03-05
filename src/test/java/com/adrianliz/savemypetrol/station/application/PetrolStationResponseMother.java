package com.adrianliz.savemypetrol.station.application;

import com.adrianliz.savemypetrol.station.domain.PetrolStation;

public final class PetrolStationResponseMother {

  public static PetrolStationResponse create(final PetrolStation petrolStation) {
    return PetrolStationResponse.from(petrolStation);
  }
}
