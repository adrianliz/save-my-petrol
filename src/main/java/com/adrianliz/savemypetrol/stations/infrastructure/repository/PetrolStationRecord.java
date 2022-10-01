package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "petrol_station")
@Data
@Builder
public final class PetrolStationRecord {
  @Id private long id;

  private String name;

  private String address;

  private double latitude;

  private double longitude;

  private List<PetrolStationProductRecord> products;
}
