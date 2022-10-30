package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.Identifier;

public class PetrolStationId extends Identifier {
  private final Long value;

  public PetrolStationId(final Long value) {
    validate(value);
    this.value = value;
  }

  private void validate(final Long value) {
    if (!super.isValid(value)) {
      throw new InvalidPetrolStationId();
    }
  }

  public Long getPrimitive() {
    return value;
  }
}
