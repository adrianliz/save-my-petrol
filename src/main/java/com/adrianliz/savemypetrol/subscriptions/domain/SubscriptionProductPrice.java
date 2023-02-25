package com.adrianliz.savemypetrol.subscriptions.domain;

import java.io.Serializable;

public final class SubscriptionProductPrice implements Serializable {
  private final Long cents;

  public SubscriptionProductPrice(final Long cents) {
    validate(cents);
    this.cents = cents;
  }

  private void validate(final Long cents) {
    if (cents == null || cents <= 0) {
      throw new InvalidSubscriptionPrice();
    }
  }

  public Long cents() {
    return cents;
  }
}
