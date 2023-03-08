package com.adrianliz.savemypetrol.payment.infrastructure.repository.record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class PaymentSubscriptionRecord {
  private Long startTimestamp;
  private Long endTimestamp;
  private Long cancelTimestamp;
}
