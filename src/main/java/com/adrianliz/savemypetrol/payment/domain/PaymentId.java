package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.UUIDIdentifier;
import java.util.Objects;

public final class PaymentId extends UUIDIdentifier {
  private final String value;

  public PaymentId(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    if (!super.isValid(value)) {
      throw new InvalidPaymentId();
    }
  }

  public String value() {
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
    final PaymentId that = (PaymentId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
