package com.adrianliz.savemypetrol.subscription.infrastructure.repository.record;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class SubscriptionRecord {
  @Id @EqualsAndHashCode.Include private UUID id;

  private Long userId;

  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
  private GeoJsonPoint sourceLocation;

  private Long maxMetersFromSource;

  private SubscriptionTargetProductRecord targetProduct;
}
