package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.Identifier;
import java.util.Objects;

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

  public Long value() {
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
    final PetrolStationId that = (PetrolStationId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
