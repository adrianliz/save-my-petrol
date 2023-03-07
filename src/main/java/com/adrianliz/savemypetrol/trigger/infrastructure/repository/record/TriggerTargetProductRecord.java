package com.adrianliz.savemypetrol.trigger.infrastructure.repository.record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class TriggerTargetProductRecord {

  private Integer typeId;

  private Long cents;
}
