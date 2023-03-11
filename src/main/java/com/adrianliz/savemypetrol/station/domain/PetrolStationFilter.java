package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.Location;
import com.adrianliz.savemypetrol.station.domain.exception.InvalidPetrolStationFilter;

public class PetrolStationFilter {

  private final PetrolStationLocation sourceLocation;
  private final Double maxMetersFromSource;

  public PetrolStationFilter(
      final PetrolStationLocation sourceLocation, final Double maxMetersFromSource) {

    validate(sourceLocation, maxMetersFromSource);
    this.sourceLocation = sourceLocation;
    this.maxMetersFromSource = maxMetersFromSource;
  }

  private void validate(final Location sourceLocation, final Double maxMetersFromSource) {

    if (sourceLocation == null || maxMetersFromSource == null) {
      throw new InvalidPetrolStationFilter();
    }
    final double tenKmInMeters = 10000D;
    final double oneHundredKmInMeters = 10000D;
    if (Double.compare(maxMetersFromSource, tenKmInMeters) < 0
        || Double.compare(maxMetersFromSource, oneHundredKmInMeters) > 1) {
      throw new InvalidPetrolStationFilter();
    }
  }

  public Double sourceLatitude() {
    return sourceLocation.latitude();
  }

  public Double sourceLongitude() {
    return sourceLocation.longitude();
  }

  public Double maxKmFromSource() {
    return (maxMetersFromSource != null && maxMetersFromSource > 0)
        ? maxMetersFromSource / 1000
        : 0;
  }

  @Override
  public String toString() {
    return "PetrolStationFilter(sourceLocation="
        + sourceLatitude()
        + ", "
        + sourceLongitude()
        + ", maxMetersFromSource="
        + maxMetersFromSource
        + ")";
  }

  public static class PetrolStationFilterBuilder {

    private PetrolStationLocation sourceLocation;
    private Double maxMetersFromSource;

    public PetrolStationFilterBuilder() {}

    public PetrolStationFilterBuilder sourceLocation(final PetrolStationLocation sourceLocation) {
      this.sourceLocation = sourceLocation;
      return this;
    }

    public PetrolStationFilterBuilder maxMetersFromSource(final Double maxMetersFromSource) {
      this.maxMetersFromSource = maxMetersFromSource;
      return this;
    }

    public PetrolStationFilter build() {
      return new PetrolStationFilter(sourceLocation, maxMetersFromSource);
    }
  }
}
