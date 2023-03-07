package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.Date;
import com.adrianliz.savemypetrol.payment.domain.exception.InvalidPaymentSubscriptionEndDate;
import java.time.LocalDateTime;
import java.util.Objects;

public final class PaymentSubscriptionEndDate extends Date {

  public PaymentSubscriptionEndDate(final LocalDateTime value) {
    super(value);
  }

  @Override
  protected void validate(final LocalDateTime value) {
    if (value == null) {
      throw new InvalidPaymentSubscriptionEndDate();
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
    final PaymentSubscriptionEndDate date = (PaymentSubscriptionEndDate) o;
    return Objects.equals(value, date.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
