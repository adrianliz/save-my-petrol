package com.adrianliz.savemypetrol.subscriptions.domain;

import com.adrianliz.savemypetrol.common.domain.UUID;

public final class SubscriptionId extends UUID {
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
}
