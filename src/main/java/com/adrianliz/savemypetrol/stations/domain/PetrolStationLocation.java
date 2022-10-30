package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;

public class PetrolStationLocation extends LocationValueObject {
  private final String address;

  public PetrolStationLocation(
      final Double latitude, final Double longitude, final String address) {

    super(latitude, longitude);
    validate();
    this.address = address;
  }

  public PetrolStationLocation(final LocationValueObject location, final String address) {
    this(location.getLatitude(), location.getLongitude(), address);
  }

  private void validate() {
    if (address == null || address.isBlank() || address.length() > 100) {
      throw new InvalidPetrolStationLocation();
    }
  }

  public String address() {
    return address;
  }
}
