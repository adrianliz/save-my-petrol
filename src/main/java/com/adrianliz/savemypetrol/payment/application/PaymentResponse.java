package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class PaymentResponse {
  private final Long userId;
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;

  public static PaymentResponse from(final Payment payment) {
    final var subscription = payment.subscription();
    return new PaymentResponse(
        payment.user().id().value(),
        subscription.startDate().value(),
        subscription.endDate().value());
  }
}
