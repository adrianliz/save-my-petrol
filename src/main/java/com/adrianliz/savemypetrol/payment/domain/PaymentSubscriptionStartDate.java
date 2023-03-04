package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.Date;
import java.time.LocalDateTime;

public final class PaymentSubscriptionStartDate extends Date {
  public PaymentSubscriptionStartDate(final LocalDateTime value) {
    super(value);
  }

  @Override
  protected void validate(final LocalDateTime value) {
    if (value == null) {
      throw new InvalidPaymentSubscriptionStartDate();
    }
  }

  public boolean isBefore(final PaymentSubscriptionEndDate endDate) {
    return value.isBefore(endDate.value());
  }
}
