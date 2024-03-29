package com.adrianliz.savemypetrol.station.infrastructure.repository;

import com.adrianliz.savemypetrol.station.domain.PetrolStationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.query.NearQuery;

@Slf4j
public final class MongoPetrolStationFilter {

  public static NearQuery from(final PetrolStationFilter filter) {
    log.info("MongoPetrolStationFilter> Filtering by: {}", filter);
    return NearQuery.near(filter.sourceLongitude(), filter.sourceLatitude())
        .maxDistance(filter.maxKmFromSource(), Metrics.KILOMETERS)
        .spherical(true);
  }
}
