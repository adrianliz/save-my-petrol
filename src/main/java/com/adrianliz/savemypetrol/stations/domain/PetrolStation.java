package com.adrianliz.savemypetrol.stations.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PetrolStation implements Serializable {
  private final PetrolStationId id;
  private final PetrolStationName name;
  private final PetrolStationLocation location;
  private final List<PetrolStationProduct> products;

  public PetrolStation(
      final PetrolStationId id,
      final PetrolStationName name,
      final PetrolStationLocation location,
      final List<PetrolStationProduct> products) {

    this.id = id;
    this.name = name;
    this.location = location;
    this.products = products;
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

  public List<PetrolStationProduct> products() {
    return products;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PetrolStation that = (PetrolStation) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
