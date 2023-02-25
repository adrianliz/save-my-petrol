package com.adrianliz.savemypetrol.subscriptions.domain;

import com.adrianliz.savemypetrol.common.domain.Identifier;

public final class SubscriptionProductId extends Identifier {
  private final Long value;

  public SubscriptionProductId(final Long value) {
    validate(value);
    this.value = value;
  }

  private void validate(final Long value) {
    if (!super.isValid(value)) {
      throw new InvalidSubscriptionId();
    }
  }

  public Long value() {
    return value;
  }
}
