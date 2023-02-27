package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.Location;

public class PetrolStationLocation extends Location {
  private final String address;

  public PetrolStationLocation(
      final Double latitude, final Double longitude, final String address) {

    super(latitude, longitude);
    validate(address);
    this.address = address;
  }

  public PetrolStationLocation(final Location location, final String address) {
    this(location.latitude(), location.longitude(), address);
  }

  private void validate(final String address) {
    if (address == null || address.isBlank() || address.length() > 1000) {
      throw new InvalidPetrolStationLocation();
    }
  }

  public String address() {
    return address;
  }
}
