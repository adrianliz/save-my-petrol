package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.IncrementalIdentifier;
import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTriggerTargetUserId;
import java.util.Objects;

public final class TriggerTargetUserId extends IncrementalIdentifier {

  public TriggerTargetUserId(final Long value) {
    super(value);
  }

  @Override
  protected void validate(final Long value) {
    if (value == null || value <= 0) {
      throw new InvalidTriggerTargetUserId();
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
    final TriggerTargetUserId that = (TriggerTargetUserId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
