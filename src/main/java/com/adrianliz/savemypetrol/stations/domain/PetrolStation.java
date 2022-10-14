package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import java.io.Serializable;
import java.util.List;

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

  public boolean isInBoundaryWith(
      final LocationValueObject targetLocation, final Double maxMetersAround) {

    return location.isInBoundaryWith(targetLocation, maxMetersAround);
  }
}
