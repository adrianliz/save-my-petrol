package com.adrianliz.savemypetrol.subscription.infrastructure.api;

import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.subscription.domain.Subscription;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionId;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionTargetProduct;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionTriggerProductPrice;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionUser;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionUserDistance;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionUserId;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionUserLocation;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class CreateSubscriptionRequest {
  @NotNull public final Long userId;

  @NotNull private final Double sourceLatitude;

  @NotNull private final Double sourceLongitude;

  @NotNull private final Long maxMetersFromSource;

  @NotNull @Valid private TargetProduct targetProduct;

  @AllArgsConstructor
  private static final class TargetProduct {
    @NotNull final Integer id;
    @NotNull final Long triggerPrice;
  }

  public Subscription buildSubscription() {
    return new Subscription(
        new SubscriptionId(UUID.randomUUID().toString()),
        new SubscriptionUser(
            new SubscriptionUserId(userId),
            new SubscriptionUserLocation(sourceLatitude, sourceLongitude),
            new SubscriptionUserDistance(maxMetersFromSource)),
        new SubscriptionTargetProduct(
            ProductType.findById(targetProduct.id),
            new SubscriptionTriggerProductPrice(targetProduct.triggerPrice)));
  }
}
