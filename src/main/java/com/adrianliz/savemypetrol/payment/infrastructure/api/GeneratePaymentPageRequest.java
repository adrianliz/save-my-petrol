package com.adrianliz.savemypetrol.payment.infrastructure.api;

import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Jacksonized
@Builder
public final class GeneratePaymentPageRequest {

  private final Long paymentUserId;

  public PaymentUserId buildPaymentUserId() {
    return new PaymentUserId(paymentUserId);
  }
}
