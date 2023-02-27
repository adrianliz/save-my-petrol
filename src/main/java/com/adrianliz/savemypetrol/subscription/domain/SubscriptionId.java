package com.adrianliz.savemypetrol.subscription.domain;

import com.adrianliz.savemypetrol.common.domain.UUIDIdentifier;
import java.util.Objects;

public final class SubscriptionId extends UUIDIdentifier {
  private final String value;

  public SubscriptionId(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    if (!super.isValid(value)) {
      throw new InvalidSubscriptionId();
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
    final SubscriptionId that = (SubscriptionId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
