package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.PaymentPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@AllArgsConstructor
@Getter
public final class PaymentPageResponse {

  private final String url;

  public static PaymentPageResponse from(final PaymentPage paymentPage) {
    return new PaymentPageResponse(paymentPage.url());
  }
}
