package com.adrianliz.savemypetrol.trigger.domain;

public final class TriggerMother {

  public static Trigger create(
      final TriggerId id,
      final TriggerTargetUser targetUser,
      final TriggerTargetProduct targetProduct) {
    return new Trigger(id, targetUser, targetProduct);
  }

  public static Trigger random() {
    return create(
        TriggerIdMother.random(),
        TriggerTargetUserMother.random(),
        TriggerTargetProductMother.random());
  }
}
