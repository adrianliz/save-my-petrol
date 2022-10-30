package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.StringValueObject;

public final class PetrolStationName extends StringValueObject {
  private final String value;

  public PetrolStationName(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    if (!super.isValid(value) || value.length() > 100) {
      throw new InvalidPetrolStationName();
    }
  }

  public String getPrimitive() {
    return value;
  }
}
