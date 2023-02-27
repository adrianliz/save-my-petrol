package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.LongMother;

public final class PetrolStationIdMother {
  public static PetrolStationId create(final Long value) {
    return new PetrolStationId(value);
  }

  public static PetrolStationId random() {
    long id;
    do {
      id = LongMother.random();
    } while (id <= 0);
    return create(id);
  }
}
