package com.adrianliz.savemypetrol.subscription.domain;

import com.adrianliz.savemypetrol.common.domain.IncrementalIdentifier;
import java.util.Objects;

public final class SubscriptionUserId extends IncrementalIdentifier {
  private final Long value;

  public SubscriptionUserId(final Long value) {
    validate(value);
    this.value = value;
  }

  private void validate(final Long value) {
    if (!super.isValid(value)) {
      throw new InvalidSubscriptionUserId();
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
    final SubscriptionUserId that = (SubscriptionUserId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
