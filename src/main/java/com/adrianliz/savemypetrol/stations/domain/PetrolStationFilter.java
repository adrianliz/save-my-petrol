package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import com.adrianliz.savemypetrol.common.domain.Page;

public class PetrolStationFilter {
  private final LocationValueObject sourceLocation;
  private final Double maxMetersFromSource;
  private final Page pageRequested;

  public PetrolStationFilter(
      final LocationValueObject sourceLocation,
      final Double maxMetersFromSource,
      final Page pageRequested) {

    this.sourceLocation = sourceLocation;
    this.maxMetersFromSource = maxMetersFromSource;
    this.pageRequested = pageRequested;
  }

  public Double getSourceLatitude() {
    return sourceLocation.getLatitude();
  }

  public Double getSourceLongitude() {
    return sourceLocation.getLongitude();
  }

  public Double getMaxKmFromSource() {
    return (maxMetersFromSource != null && maxMetersFromSource > 0)
        ? maxMetersFromSource / 1000
        : 0;
  }

  public Integer maxElements() {
    return pageRequested.calculateMaxElements();
  }

  public Integer offset() {
    return pageRequested.calculateSkippedElements();
  }

  public static class PetrolStationFilterBuilder {
    private LocationValueObject sourceLocation;
    private Double maxMetersFromSource;
    private Page pageRequested;

    public PetrolStationFilterBuilder() {}

    public PetrolStationFilterBuilder sourceLocation(final LocationValueObject sourceLocation) {
      this.sourceLocation = sourceLocation;
      return this;
    }

    public PetrolStationFilterBuilder maxMetersFromSource(final Double maxMetersFromSource) {
      this.maxMetersFromSource = maxMetersFromSource;
      return this;
    }

    public PetrolStationFilterBuilder pageRequested(final Page pageRequested) {
      this.pageRequested = pageRequested;
      return this;
    }

    public PetrolStationFilter build() {
      return new PetrolStationFilter(sourceLocation, maxMetersFromSource, pageRequested);
    }
  }
}
