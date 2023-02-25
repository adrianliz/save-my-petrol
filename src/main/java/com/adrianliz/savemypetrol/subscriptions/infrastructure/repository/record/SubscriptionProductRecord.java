package com.adrianliz.savemypetrol.subscriptions.infrastructure.repository.record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class SubscriptionProductRecord {
  private Integer typeId;

  private Long cents;
}
