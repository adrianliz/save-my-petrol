package com.adrianliz.savemypetrol.subscriptions.infrastructure.api;

import com.adrianliz.savemypetrol.products.domain.ProductType;
import com.adrianliz.savemypetrol.subscriptions.domain.Subscription;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionId;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionProduct;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionProductPrice;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUser;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUserDistance;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUserId;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUserLocation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class CreateSubscriptionRequest {
  @NotNull public final Long userId;

  @NotNull private final Double sourceLatitude;

  @NotNull private final Double sourceLongitude;

  @NotNull private final Long maxDistance;

  @NotEmpty @Valid private List<TargetProduct> targetProducts;

  @AllArgsConstructor
  public static class TargetProduct {
    final Integer id;
    final Long price;
  }

  public Subscription buildSubscription() {
    return new Subscription(
        new SubscriptionId(UUID.randomUUID().toString()),
        new SubscriptionUser(
            new SubscriptionUserId(userId),
            new SubscriptionUserLocation(sourceLatitude, sourceLongitude),
            new SubscriptionUserDistance(maxDistance)),
        targetProducts.stream()
            .map(
                targetProduct ->
                    new SubscriptionProduct(
                        ProductType.findById(targetProduct.id),
                        new SubscriptionProductPrice(targetProduct.price)))
            .toList());
  }
}
