package com.adrianliz.savemypetrol.trigger.domain;

public final class TriggerTargetUserMother {

  public static TriggerTargetUser create(
      final TriggerTargetUserId id,
      final TriggerTargetUserLocation sourceLocation,
      final TriggerTargetUserDistance maxDistanceFromSource) {
    return new TriggerTargetUser(id, sourceLocation, maxDistanceFromSource);
  }

  public static TriggerTargetUser random() {
    return create(
        TriggerTargetUserIdMother.random(),
        TriggerTargetUserLocationMother.random(),
        TriggerTargetUserDistanceMother.random());
  }

  public static TriggerTargetUser randomWithTargetUserDistante(
      final TriggerTargetUserDistance targetUserDistance) {
    return create(
        TriggerTargetUserIdMother.random(),
        TriggerTargetUserLocationMother.random(),
        targetUserDistance);
  }
}
