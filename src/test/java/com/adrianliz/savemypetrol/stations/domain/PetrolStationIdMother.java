package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.LongMother;

public final class PetrolStationIdMother {
  public static PetrolStationId create(final Long value) {
    return new PetrolStationId(value);
  }

  public static PetrolStationId random() {
    return create(LongMother.random());
  }
}
