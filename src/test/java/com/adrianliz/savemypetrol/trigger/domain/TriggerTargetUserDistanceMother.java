package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.LongMother;

public final class TriggerTargetUserDistanceMother {

  public static TriggerTargetUserDistance create(final Long value) {
    return new TriggerTargetUserDistance(value);
  }

  public static TriggerTargetUserDistance random() {
    long distance;
    do {
      distance = LongMother.random();
    } while (distance <= 0);
    return create(distance);
  }
}
