package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.StringMother;

public final class PetrolStationNameMother {

  public static PetrolStationName create(final String value) {
    return new PetrolStationName(value);
  }

  public static PetrolStationName random() {
    return create(StringMother.random());
  }
}
