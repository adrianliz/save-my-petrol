package com.adrianliz.savemypetrol.station.infrastructure.repository.record;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "petrol_station")
@Data
@Builder
public final class PetrolStationRecord {

  @Id
  private long id;

  private String name;

  private String address;

  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
  private GeoJsonPoint location;

  private List<PetrolStationProductRecord> products;
}
