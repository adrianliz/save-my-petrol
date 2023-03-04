package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.Date;
import java.time.LocalDateTime;

public final class PaymentSubscriptionCancelDate extends Date {
  public PaymentSubscriptionCancelDate(final LocalDateTime value) {
    super(value);
  }

  @Override
  public LocalDateTime value() {
    return value;
  }

  @Override
  protected void validate(final LocalDateTime value) {
    if (value == null) {
      throw new InvalidPaymentSubscriptionCancelDate();
    }
  }
}
