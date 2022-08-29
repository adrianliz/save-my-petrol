package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import com.adrianliz.savemypetrol.common.domain.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Builder
public class PetrolStationsFilter {
  private final LocationValueObject sourceLocation;
  private final Double maxMetersFromSource;
  private final Page pageRequested;

  public Flux<PetrolStation> applyTo(final Flux<PetrolStation> petrolStations) {
    final var targetPetrolStations =
        petrolStations
            .filter(
                petrolStation ->
                    petrolStation.isInBoundaryWith(sourceLocation, maxMetersFromSource))
            .skip(pageRequested.calculateSkippedElements());

    return pageRequested.hasMaxElements()
        ? targetPetrolStations.take(pageRequested.calculateMaxElements())
        : targetPetrolStations;
  }
}
