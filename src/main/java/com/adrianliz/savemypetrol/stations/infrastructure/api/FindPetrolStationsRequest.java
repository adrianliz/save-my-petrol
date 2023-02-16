package com.adrianliz.savemypetrol.stations.infrastructure.api;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import com.adrianliz.savemypetrol.common.domain.Page;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPetrolStationsRequest {
  @NotNull private final Double sourceLatitude;

  @NotNull private final Double sourceLongitude;

  @Min(10000)
  @Max(100000)
  private final Double maxMetersFromSource;

  private final Integer maxElements;

  private final Integer offset;

  public PetrolStationFilter buildFilter() {
    final var filter = new PetrolStationFilter.PetrolStationFilterBuilder();
    filter.maxMetersFromSource(maxMetersFromSource != null ? maxMetersFromSource : 10000);
    filter.sourceLocation(new LocationValueObject(sourceLatitude, sourceLongitude) {});
    filter.pageRequested(Page.of(maxElements, offset));

    return filter.build();
  }
}
