package com.adrianliz.savemypetrol.trigger.infrastructure.api;

import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerId;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetProduct;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetProductPrice;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUser;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserDistance;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserLocation;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Jacksonized
@Builder
@Getter
public final class CreateTriggerRequest {

  @NotNull private final Long userId;

  @NotNull private final Double sourceLatitude;

  @NotNull private final Double sourceLongitude;

  @NotNull private final Long maxMetersFromSource;

  @NotNull @Valid private TargetProduct targetProduct;

  public Trigger buildTrigger() {
    return new Trigger(
        new TriggerId(UUID.randomUUID().toString()),
        new TriggerTargetUser(
            new TriggerTargetUserId(userId),
            new TriggerTargetUserLocation(sourceLatitude, sourceLongitude),
            new TriggerTargetUserDistance(maxMetersFromSource)),
        new TriggerTargetProduct(
            ProductType.findById(targetProduct.id),
            new TriggerTargetProductPrice(targetProduct.cents)));
  }

  @AllArgsConstructor
  @Getter
  private static final class TargetProduct {

    @NotNull final Integer id;
    @NotNull final Long cents;
  }
}
