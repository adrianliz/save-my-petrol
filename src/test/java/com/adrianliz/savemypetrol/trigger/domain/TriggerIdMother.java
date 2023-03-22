package com.adrianliz.savemypetrol.trigger.domain;

import java.util.UUID;

public final class TriggerIdMother {

  public static TriggerId create(final String value) {
    return new TriggerId(value);
  }

  public static TriggerId random() {
    return create(UUID.randomUUID().toString());
  }
}
