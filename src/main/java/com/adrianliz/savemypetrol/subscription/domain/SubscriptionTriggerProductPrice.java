package com.adrianliz.savemypetrol.subscription.domain;

import java.io.Serializable;
import java.util.Objects;

public final class SubscriptionTriggerProductPrice implements Serializable {
  private final Long cents;

  public SubscriptionTriggerProductPrice(final Long cents) {
    validate(cents);
    this.cents = cents;
  }

  private void validate(final Long cents) {
    if (cents == null || cents <= 0) {
      throw new InvalidSubscriptionTriggerProductPrice();
    }
  }

  public Long cents() {
    return cents;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SubscriptionTriggerProductPrice that = (SubscriptionTriggerProductPrice) o;
    return Objects.equals(cents, that.cents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cents);
  }
}
