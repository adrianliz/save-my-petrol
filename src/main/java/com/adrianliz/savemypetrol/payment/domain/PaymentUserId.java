package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.IncrementalIdentifier;
import java.util.Objects;

public final class PaymentUserId extends IncrementalIdentifier {

  public PaymentUserId(final Long value) {
    super(value);
  }

  @Override
  protected void validate(final Long value) {
    if (value == null || value <= 0) {
      throw new InvalidPaymentUserId();
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
    final PaymentUserId that = (PaymentUserId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
