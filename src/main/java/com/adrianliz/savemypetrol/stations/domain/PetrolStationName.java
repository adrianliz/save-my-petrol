package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.StringValueObject;
import java.util.Objects;

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

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PetrolStationName that = (PetrolStationName) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
