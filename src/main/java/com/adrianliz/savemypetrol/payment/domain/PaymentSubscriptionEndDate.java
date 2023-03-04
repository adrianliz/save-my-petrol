package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.Date;
import java.time.LocalDateTime;

public final class PaymentSubscriptionEndDate extends Date {
  public PaymentSubscriptionEndDate(final LocalDateTime value) {
    super(value);
  }

  @Override
  public LocalDateTime value() {
    return value;
  }

  @Override
  protected void validate(final LocalDateTime value) {
    if (value == null) {
      throw new InvalidPaymentSubscriptionEndDate();
    }
  }

  public boolean isAfter(final PaymentSubscriptionCancelDate cancelDate) {
    return value.isAfter(cancelDate.value());
  }
}
