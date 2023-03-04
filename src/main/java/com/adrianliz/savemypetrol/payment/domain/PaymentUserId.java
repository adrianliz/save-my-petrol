package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.IncrementalIdentifier;
import java.util.Objects;

public final class PaymentUserId extends IncrementalIdentifier {
  private final Long value;

  public PaymentUserId(final Long value) {
    validate(value);
    this.value = value;
  }

  private void validate(final Long value) {
    if (!super.isValid(value)) {
      throw new InvalidPaymentUserId();
    }
  }

  public Long value() {
    return value;
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
