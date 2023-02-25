package com.adrianliz.savemypetrol.subscriptions.infrastructure.repository.record;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

@Data
@Builder
public final class SubscriptionUserRecord {
  private Long userId;

  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
  private GeoJsonPoint location;

  private Long maxDistance;
}
