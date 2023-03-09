package com.adrianliz.savemypetrol.match.domain;

import com.adrianliz.savemypetrol.match.domain.exception.InvalidPetrolStationMatch;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationName;
import com.adrianliz.savemypetrol.station.domain.PetrolStationProduct;
import java.io.Serializable;
import java.util.Objects;

public final class PetrolStationMatch implements Serializable {

  private final PetrolStationId id;
  private final PetrolStationName name;
  private final PetrolStationLocation location;
  private final PetrolStationProduct product;

  public PetrolStationMatch(
      final PetrolStationId id,
      final PetrolStationName name,
      final PetrolStationLocation location,
      final PetrolStationProduct product) {
    validate(id, name, location, product);
    this.id = id;
    this.name = name;
    this.location = location;
    this.product = product;
  }

  private void validate(
      final PetrolStationId id,
      final PetrolStationName name,
      final PetrolStationLocation location,
      final PetrolStationProduct product) {

    if (id == null || name == null || location == null || product == null) {
      throw new InvalidPetrolStationMatch();
    }
  }

  public PetrolStationId id() {
    return id;
  }

  public PetrolStationName name() {
    return name;
  }

  public PetrolStationLocation location() {
    return location;
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
