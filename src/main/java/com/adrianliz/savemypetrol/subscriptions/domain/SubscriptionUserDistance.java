package com.adrianliz.savemypetrol.subscriptions.domain;

import java.io.Serializable;

public final class SubscriptionUserDistance implements Serializable {
  private final Long meters;

  public SubscriptionUserDistance(final Long meters) {
    validate(meters);
    this.meters = meters;
  }

  private void validate(final Long meters) {
    if (meters <= 0) {
      throw new InvalidSubscriptionUserDistance();
    }
  }

  public Long meters() {
    return meters;
  }
}
