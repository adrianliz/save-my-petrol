package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;

public class PetrolStationLocation extends LocationValueObject {
  private final String address;

  public PetrolStationLocation(
      final Double latitude, final Double longitude, final String address) {

    super(latitude, longitude);
    this.address = address;
  }

  public String address() {
    return address;
  }
}
