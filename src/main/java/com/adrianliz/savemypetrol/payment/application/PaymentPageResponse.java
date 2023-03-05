package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.PaymentPage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class PaymentPageResponse {

  private final String url;

  public static PaymentPageResponse from(final PaymentPage paymentPage) {
    return new PaymentPageResponse(paymentPage.url());
  }
}
