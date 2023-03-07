package com.adrianliz.savemypetrol.match.domain;

import com.adrianliz.savemypetrol.match.domain.exception.InvalidPetrolStationMatch;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import com.adrianliz.savemypetrol.station.domain.PetrolStationProduct;
import java.io.Serializable;
import java.util.Objects;

public final class PetrolStationMatch implements Serializable {

  private final PetrolStationId id;
  private final PetrolStationProduct product;

  public PetrolStationMatch(final PetrolStationId id, final PetrolStationProduct product) {
    validate(id, product);
    this.id = id;
    this.product = product;
  }

  private void validate(final PetrolStationId id, final PetrolStationProduct product) {
    if (id == null || product == null) {
      throw new InvalidPetrolStationMatch();
    }
  }

  public PetrolStationId id() {
    return id;
  }

  public PetrolStationProduct product() {
    return product;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PetrolStationMatch that = (PetrolStationMatch) o;
    return Objects.equals(id, that.id) && Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, product);
  }
}
