package com.adrianliz.savemypetrol.station.infrastructure.api;

import com.adrianliz.savemypetrol.station.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Jacksonized
@Builder
@Getter
public class FindPetrolStationsRequest {

  @NotNull private final Double sourceLatitude;

  @NotNull private final Double sourceLongitude;

  @Min(5000)
  @Max(25000)
  private final Double maxMetersFromSource;

  public PetrolStationFilter buildFilter() {
    return new PetrolStationFilter.PetrolStationFilterBuilder()
        .maxMetersFromSource(maxMetersFromSource != null ? maxMetersFromSource : 10000)
        .sourceLocation(new PetrolStationLocation(sourceLatitude, sourceLongitude))
        .build();
  }
}
