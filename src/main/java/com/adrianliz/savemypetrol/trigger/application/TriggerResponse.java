package com.adrianliz.savemypetrol.trigger.application;

import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class TriggerResponse {
  private final String id;
  private final Double sourceLatitude;
  private final Double sourceLongitude;
  private final Long maxMetersFromSource;
  private TargetProduct targetProduct;

  public static TriggerResponse from(final Trigger trigger) {
    final var targetUser = trigger.targetUser();
    final var targetProduct = trigger.targetProduct();
    return new TriggerResponse(
        trigger.id().valueAsString(),
        targetUser.sourceLocation().latitude(),
        targetUser.sourceLocation().longitude(),
        targetUser.maxDistanceFromSource().meters(),
        new TargetProduct(
            targetProduct.type().id(),
            targetProduct.type().label(),
            targetProduct.price().cents()));
  }

  @AllArgsConstructor
  @Getter
  private static final class TargetProduct {
    final Integer id;
    final String label;
    final Long cents;
  }
}
