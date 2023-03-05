package com.adrianliz.savemypetrol.subscription.infrastructure.repository;

import com.adrianliz.savemypetrol.subscription.domain.Subscription;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionTargetProduct;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionUser;
import com.adrianliz.savemypetrol.subscription.infrastructure.repository.record.SubscriptionRecord;
import com.adrianliz.savemypetrol.subscription.infrastructure.repository.record.SubscriptionTargetProductRecord;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public final class SubscriptionConverter {

  public static SubscriptionRecord toRecord(final Subscription subscription) {
    final SubscriptionUser user = subscription.user();
    final SubscriptionTargetProduct targetProduct = subscription.targetProduct();

    return SubscriptionRecord.builder()
        .id(subscription.id().value())
        .userId(user.id().value())
        .sourceLocation(
            new GeoJsonPoint(user.sourceLocation().longitude(), user.sourceLocation().latitude()))
        .maxMetersFromSource(user.maxDistanceFromSource().meters())
        .targetProduct(
            SubscriptionTargetProductRecord.builder()
                .typeId(targetProduct.type().id())
                .triggerCents(targetProduct.triggerPrice().cents())
                .build())
        .build();
  }
}
