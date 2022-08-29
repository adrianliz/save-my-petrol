package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public abstract class LocationValueObject implements Serializable {

  private final Double latitude;
  private final Double longitude;

  // See: https://stackoverflow.com/a/16794680
  private Double distanceTo(final LocationValueObject targetLocation) {
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

  public boolean isInBoundaryWith(
      final LocationValueObject targetLocation, final Double maxMetersAround) {

    return distanceTo(targetLocation) <= maxMetersAround;
  }
}
