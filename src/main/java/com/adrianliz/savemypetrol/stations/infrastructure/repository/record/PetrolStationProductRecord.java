package com.adrianliz.savemypetrol.stations.infrastructure.repository.record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class PetrolStationProductRecord {
  private Integer typeId;

  private Long cents;

  private String currency;
}
