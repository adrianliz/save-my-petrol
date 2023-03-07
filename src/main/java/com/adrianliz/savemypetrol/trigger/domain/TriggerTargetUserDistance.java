package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTriggerTargetUserDistance;
import java.io.Serializable;
import java.util.Objects;

public final class TriggerTargetUserDistance implements Serializable {

  private final Long meters;

  public TriggerTargetUserDistance(final Long meters) {
    validate(meters);
    this.meters = meters;
  }

  private void validate(final Long meters) {
    if (meters == null || meters <= 0) {
      throw new InvalidTriggerTargetUserDistance();
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
    final TriggerTargetUserDistance that = (TriggerTargetUserDistance) o;
    return Objects.equals(meters, that.meters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meters);
  }
}
