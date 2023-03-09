package com.adrianliz.savemypetrol.payment.infrastructure.api;

import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Jacksonized
@Builder
@Getter
public final class GeneratePaymentPageRequest {

  @NotNull private final Long userId;

  public PaymentUserId buildPaymentUserId() {
    return new PaymentUserId(userId);
  }
}
