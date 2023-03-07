package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.common.domain.UUIDIdentifier;
import com.adrianliz.savemypetrol.match.domain.exception.InvalidFindMatchesProcessId;
import java.util.Objects;
import java.util.UUID;

public final class PaymentId extends UUIDIdentifier {

  public PaymentId(final String value) {
    super(value);
  }

  @Override
  protected UUID validate(final String value) {
    if (value == null || value.isBlank()) {
      throw new InvalidFindMatchesProcessId();
    }
    try {
      return UUID.fromString(value);
    } catch (final IllegalArgumentException e) {
      throw new InvalidFindMatchesProcessId();
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
    final PaymentId that = (PaymentId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
