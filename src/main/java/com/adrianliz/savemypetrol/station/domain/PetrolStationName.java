package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.StringValueObject;
import java.util.Objects;

public final class PetrolStationName extends StringValueObject {

  public PetrolStationName(final String value) {
    super(value);
  }

  @Override
  protected void validate(final String value) {
    if (value == null || value.isBlank() || value.length() > 100) {
      throw new InvalidPetrolStationName();
    }
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
