package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.LongMother;

public final class TriggerTargetUserIdMother {

  public static TriggerTargetUserId create(final Long value) {
    return new TriggerTargetUserId(value);
  }

  public static TriggerTargetUserId random() {
    long id;
    do {
      id = LongMother.random();
    } while (id <= 0);
    return create(id);
  }
}
