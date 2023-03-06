package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.Date;
import java.time.LocalDateTime;
import java.util.Objects;

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

  public boolean isAfter(final PaymentSubscriptionEndDate endDate) {
    return value.isAfter(endDate.value());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PaymentSubscriptionStartDate date = (PaymentSubscriptionStartDate) o;
    return Objects.equals(value, date.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
