package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

public abstract class Location implements Serializable {

  protected final Double latitude;
  protected final Double longitude;

  public Location(final Double latitude, final Double longitude) {
    validate(latitude, longitude);
    this.latitude = latitude;
    this.longitude = longitude;
  }

  protected abstract void validate(final Double latitude, final Double longitude);

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  protected boolean coordinatesAreValid(final Double latitude, final Double longitude) {
    return latitude != null
        && longitude != null
        && !latitude.isNaN()
        && !longitude.isNaN()
        && latitude.compareTo(-90.0) > 0
        && latitude.compareTo(90.0) < 0
        && longitude.compareTo(-180.0) > 0
        && longitude.compareTo(180.0) < 0;
  }

  // SEE: https://stackoverflow.com/a/16794680 (in meters)
  public double distanceTo(final Location targetLocation) {
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

  public Double latitude() {
    return latitude;
  }

  public Double longitude() {
    return longitude;
  }
}
