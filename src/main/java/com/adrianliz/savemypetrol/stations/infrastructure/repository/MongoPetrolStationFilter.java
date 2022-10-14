package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.query.NearQuery;

public final class MongoPetrolStationFilter {
  public static NearQuery from(final PetrolStationFilter filter) {
    return NearQuery.near(filter.getSourceLatitude(), filter.getSourceLongitude())
        .maxDistance(filter.getMaxKmFromSource(), Metrics.KILOMETERS)
        .spherical(true)
        .skip(filter.offset())
        .limit(filter.maxElements());
  }
}
