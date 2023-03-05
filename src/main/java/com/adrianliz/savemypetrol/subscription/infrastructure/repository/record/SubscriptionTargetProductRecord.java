package com.adrianliz.savemypetrol.subscription.infrastructure.repository.record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class SubscriptionTargetProductRecord {

  private Integer typeId;

  private Long triggerCents;
}
