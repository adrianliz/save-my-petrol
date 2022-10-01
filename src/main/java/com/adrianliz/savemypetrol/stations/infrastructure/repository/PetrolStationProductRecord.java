package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public final class PetrolStationProductRecord {
  private Integer typeId;

  private Long cents;

  private String currency;
}
