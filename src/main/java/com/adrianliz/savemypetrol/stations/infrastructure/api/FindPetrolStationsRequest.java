package com.adrianliz.savemypetrol.stations.infrastructure.api;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import com.adrianliz.savemypetrol.common.domain.Page;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsFilter;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPetrolStationsRequest {
  @NotNull private final Double sourceLatitude;

  @NotNull private final Double sourceLongitude;

  private final Integer maxElements;

  private final Integer offset;

  private final Double maxMetersFromSource;

  public PetrolStationsFilter buildFilter() {
    final var filter = PetrolStationsFilter.builder();
    filter.maxMetersFromSource(maxMetersFromSource != null ? maxMetersFromSource : 10000);
    filter.sourceLocation(new LocationValueObject(sourceLatitude, sourceLongitude) {});
    filter.pageRequested(Page.of(maxElements, offset));

    return filter.build();
  }
}
