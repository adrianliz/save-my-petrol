package com.adrianliz.savemypetrol.subscription.domain;

import com.adrianliz.savemypetrol.common.domain.IncrementalIdentifier;
import java.util.Objects;

public final class SubscriptionUserId extends IncrementalIdentifier {

  public SubscriptionUserId(final Long value) {
    super(value);
  }

  @Override
  protected void validate(final Long value) {
    if (value == null || value <= 0) {
      throw new InvalidSubscriptionUserId();
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
    final SubscriptionUserId that = (SubscriptionUserId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
