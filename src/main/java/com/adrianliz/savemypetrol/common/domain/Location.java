package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;
import java.util.Random;

public class Location implements Serializable {

  private final Double latitude;
  private final Double longitude;

  public Location(final Double latitude, final Double longitude) {
    validate(latitude, longitude);
    this.latitude = latitude;
    this.longitude = longitude;
  }

  private void validate(final Double latitude, final Double longitude) {
    if (!isValid(latitude, longitude)) {
      throw new InvalidLocation();
    }
  }

  private boolean isValid(final Double latitude, final Double longitude) {
    return latitude != null
        && longitude != null
        && !latitude.isNaN()
        && !longitude.isNaN()
        && latitude.compareTo(-90.0) > 0
        && latitude.compareTo(90.0) < 0
        && longitude.compareTo(-180.0) > 0
        && longitude.compareTo(180.0) < 0;
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

  public static Location between(final Location sourceLocation, final Location targetLocation) {

    final var latitude = pickRandomPointBetween(sourceLocation.latitude, targetLocation.latitude);
    final var longitude =
        pickRandomPointBetween(sourceLocation.longitude, targetLocation.longitude);

    return new Location(latitude, longitude);
  }

  // SEE: https://stackoverflow.com/a/16794680 (in meters)
  public Double distanceTo(final Location targetLocation) {
    final int R = 6371; // Radius of the earth

    final double latDistance = Math.toRadians(targetLocation.latitude - latitude);
    final double lonDistance = Math.toRadians(targetLocation.longitude - longitude);
    final double a =
        Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(latitude))
                * Math.cos(Math.toRadians(targetLocation.latitude))
                * Math.sin(lonDistance / 2)
                * Math.sin(lonDistance / 2);
    final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c * 1000; // convert to meters

    distance = Math.pow(distance, 2);

    return Math.sqrt(distance);
  }

  // SEE: https://gis.stackexchange.com/a/2964
  // NOTE: This is not accurate, but it's good enough for our purposes
  public Location move(final Double distanceInMeters) {
    final var latitude = this.latitude + (distanceInMeters / 111111);
    final var longitude = this.longitude + (distanceInMeters / 111111) / Math.cos(latitude);

    return new Location(latitude, longitude);
  }

  public Double latitude() {
    return latitude;
  }

  public Double longitude() {
    return longitude;
  }
}
