package com.adrianliz.savemypetrol.subscriptions.infrastructure.repository;

import com.adrianliz.savemypetrol.products.domain.ProductType;
import com.adrianliz.savemypetrol.subscriptions.domain.Subscription;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionId;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionProduct;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionProductPrice;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUser;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUserDistance;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUserId;
import com.adrianliz.savemypetrol.subscriptions.domain.SubscriptionUserLocation;
import com.adrianliz.savemypetrol.subscriptions.infrastructure.repository.record.SubscriptionProductRecord;
import com.adrianliz.savemypetrol.subscriptions.infrastructure.repository.record.SubscriptionRecord;
import com.adrianliz.savemypetrol.subscriptions.infrastructure.repository.record.SubscriptionUserRecord;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public final class SubscriptionConverter {
  public static Subscription toEntity(final SubscriptionRecord record) {
    final SubscriptionUserRecord user = record.getUser();
    final List<SubscriptionProductRecord> products = record.getProducts();

    return new Subscription(
        new SubscriptionId(record.getId().toString()),
        new SubscriptionUser(
            new SubscriptionUserId(user.getUserId()),
            new SubscriptionUserLocation(user.getLocation().getY(), user.getLocation().getX()),
            new SubscriptionUserDistance(user.getMaxDistance())),
        products.stream()
            .map(
                product ->
                    new SubscriptionProduct(
                        ProductType.findById(product.getTypeId()),
                        new SubscriptionProductPrice(product.getCents())))
            .toList());
  }

  public static SubscriptionRecord toRecord(final Subscription subscription) {
    final SubscriptionUser user = subscription.user();
    final List<SubscriptionProduct> products = subscription.products();

    return SubscriptionRecord.builder()
        .id(UUID.fromString(subscription.id().value()))
        .user(
            SubscriptionUserRecord.builder()
                .userId(user.id().value())
                .location(new GeoJsonPoint(user.location().longitude(), user.location().latitude()))
                .maxDistance(user.maxDistance().meters())
                .build())
        .products(
            products.stream()
                .map(
                    product ->
                        SubscriptionProductRecord.builder()
                            .typeId(product.type().id())
                            .cents(product.targetPrice().cents())
                            .build())
                .toList())
        .build();
  }
}
