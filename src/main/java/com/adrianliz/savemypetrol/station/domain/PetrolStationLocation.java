package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.Location;
import com.adrianliz.savemypetrol.station.domain.exception.InvalidPetrolStationLocation;
import java.util.Objects;
import java.util.Random;

public class PetrolStationLocation extends Location {

  private final String address;

  public PetrolStationLocation(
      final Double latitude, final Double longitude, final String address) {

    super(latitude, longitude);
    validate(address);
    this.address = address;
  }

  public PetrolStationLocation(final Double latitude, final Double longitude) {
    super(latitude, longitude);
    address = "Unknown address";
  }

  @Override
  protected void validate(final Double latitude, final Double longitude) {
    if (!coordinatesAreValid(latitude, longitude)) {
      throw new InvalidPetrolStationLocation();
    }
  }

  protected void validate(final String address) {
    if (address == null || address.isBlank() || address.length() > 1000) {
      throw new InvalidPetrolStationLocation();
    }
  }

  PetrolStationLocation changeAddress(final String newAddress) {
    return new PetrolStationLocation(latitude, longitude, newAddress);
  }

  // SEE: https://gis.stackexchange.com/a/2964
  // NOTE: This is not accurate, but it's good enough for our purposes
  PetrolStationLocation move(final Double distanceInMeters) {
    if (distanceInMeters == null || distanceInMeters <= 0) {
      return this;
    }

    final var latitude = super.latitude + (distanceInMeters / 111111);
    final var longitude =
        super.longitude + (distanceInMeters / (111111 / Math.cos(super.latitude)));

    return new PetrolStationLocation(latitude, longitude, address);
  }

  public PetrolStationLocation randomUntil(final PetrolStationLocation targetLocation) {
    if (targetLocation == null) {
      return this;
    }
    final Random randomGenerator = new Random();
    final var deltaIncrement = metersTo(targetLocation);
    final var offsetFromStart = randomGenerator.nextFloat() * deltaIncrement;
    return move(offsetFromStart);
  }

  public String address() {
    return address;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PetrolStationLocation location = (PetrolStationLocation) o;
    return Objects.equals(latitude, location.latitude)
        && Objects.equals(longitude, location.longitude)
        && Objects.equals(address, location.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude, address);
  }
}
