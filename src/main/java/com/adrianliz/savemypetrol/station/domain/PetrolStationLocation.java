package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.Location;
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

  public PetrolStationLocation(
      final Double latitude, final Double longitude) {

    super(latitude, longitude);
    address = "Unknown address";
  }

  public static PetrolStationLocation between(final PetrolStationLocation sourceLocation,
      final PetrolStationLocation targetLocation) {

    final var latitude = pickRandomPointBetween(sourceLocation.latitude, targetLocation.latitude);
    final var longitude =
        pickRandomPointBetween(sourceLocation.longitude, targetLocation.longitude);

    return new PetrolStationLocation(latitude, longitude);
  }

  private static double pickRandomPointBetween(final double start, final double end) {
    if (start == end) {
      return start;
    }

    final Random randomGenerator = new Random();
    final var deltaIncrement = end - start;
    final var offsetFromStart = randomGenerator.nextFloat() * deltaIncrement;
    return start + offsetFromStart;
  }

  @Override
  protected void validate(final Double latitude, final Double longitude) {
    if (!coordinatesAreValid(latitude, longitude)) {
      throw new InvalidPetrolStationLocation();
    }
  }

  protected void validate(final String address) {
    if (address == null || address.isBlank() || address.length() > 100) {
      throw new InvalidPetrolStationLocation();
    }
  }

  public PetrolStationLocation changeAddress(final String newAddress) {
    return new PetrolStationLocation(super.latitude, super.longitude, newAddress);
  }

  // SEE: https://gis.stackexchange.com/a/2964
  // NOTE: This is not accurate, but it's good enough for our purposes
  public PetrolStationLocation move(final Double distanceInMeters) {
    final var latitude = super.latitude + (distanceInMeters / 111111);
    final var longitude = super.longitude + (distanceInMeters / 111111) / Math.cos(latitude);

    return new PetrolStationLocation(latitude, longitude, address);
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
    return Objects.equals(latitude, location.latitude) && Objects.equals(
        longitude, location.longitude) && Objects.equals(address, location.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude, address);
  }
}
