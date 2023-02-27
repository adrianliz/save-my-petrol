package com.adrianliz.savemypetrol.subscription.domain;

import java.io.Serializable;
import java.util.Objects;

public final class SubscriptionUserDistance implements Serializable {
  private final Long meters;

  public SubscriptionUserDistance(final Long meters) {
    validate(meters);
    this.meters = meters;
  }

  private void validate(final Long meters) {
    if (meters == null || meters <= 0) {
      throw new InvalidSubscriptionUserDistance();
    }
  }

  public Long meters() {
    return meters;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SubscriptionUserDistance that = (SubscriptionUserDistance) o;
    return Objects.equals(meters, that.meters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meters);
  }
}
