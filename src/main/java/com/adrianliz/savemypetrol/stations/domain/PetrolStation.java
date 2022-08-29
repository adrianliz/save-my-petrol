package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.LocationValueObject;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PetrolStation implements Serializable {

  private final PetrolStationId id;
  private final PetrolStationName name;
  private final PetrolStationLocation location;
  private final List<PetrolStationProduct> products;

  public boolean isInBoundaryWith(
      final LocationValueObject targetLocation, final Double maxMetersAround) {

    return location.isInBoundaryWith(targetLocation, maxMetersAround);
  }

  public boolean hasId(final PetrolStationId id) {
    return this.id.equals(id);
  }
}
