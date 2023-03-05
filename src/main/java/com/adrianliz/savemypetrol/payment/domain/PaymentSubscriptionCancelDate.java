package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public final class PaymentSubscriptionCancelDate extends Date {

  public PaymentSubscriptionCancelDate(final LocalDateTime value) {
    super(value);
  }

  @Override
  protected void validate(final LocalDateTime value) {
    if (value == null) {
      throw new InvalidPaymentSubscriptionCancelDate();
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PaymentSubscriptionCancelDate date = (PaymentSubscriptionCancelDate) o;
    return Objects.equals(value, date.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
