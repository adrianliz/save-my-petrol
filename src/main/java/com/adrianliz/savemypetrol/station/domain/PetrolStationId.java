package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.IncrementalIdentifier;
import java.util.Objects;

public class PetrolStationId extends IncrementalIdentifier {

  public PetrolStationId(final Long value) {
    super(value);
  }

  @Override
  protected void validate(final Long value) {
    if (value == null || value <= 0) {
      throw new InvalidPetrolStationId();
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
    final PetrolStationId that = (PetrolStationId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
