package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTriggerTargetProductPrice;
import java.io.Serializable;
import java.util.Objects;

public final class TriggerTargetProductPrice implements Serializable {

  private final Long cents;

  public TriggerTargetProductPrice(final Long cents) {
    validate(cents);
    this.cents = cents;
  }

  private void validate(final Long cents) {
    if (cents == null || cents <= 0) {
      throw new InvalidTriggerTargetProductPrice();
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
    final TriggerTargetProductPrice that = (TriggerTargetProductPrice) o;
    return Objects.equals(cents, that.cents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cents);
  }
}
