package com.adrianliz.savemypetrol.trigger.infrastructure.repository;

import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerId;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetProduct;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetProductPrice;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUser;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserDistance;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserLocation;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerRecord;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerTargetProductRecord;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public final class TriggerConverter {

  public static Trigger toEntity(final TriggerRecord record) {
    final var targetUserLocation = record.getSourceLocation();
    final var targetProduct = record.getTargetProduct();

    return new Trigger(
        new TriggerId(record.getId().toString()),
        new TriggerTargetUser(
            new TriggerTargetUserId(record.getUserId()),
            new TriggerTargetUserLocation(targetUserLocation.getY(), targetUserLocation.getX()),
            new TriggerTargetUserDistance(record.getMaxMetersFromSource())),
        new TriggerTargetProduct(
            ProductType.findById(targetProduct.getTypeId()),
            new TriggerTargetProductPrice(targetProduct.getCents())));
  }

  public static TriggerRecord toRecord(final Trigger trigger) {
    final TriggerTargetUser user = trigger.targetUser();
    final TriggerTargetProduct targetProduct = trigger.targetProduct();

    return TriggerRecord.builder()
        .id(trigger.id().value())
        .userId(user.id().value())
        .sourceLocation(
            new GeoJsonPoint(user.sourceLocation().longitude(), user.sourceLocation().latitude()))
        .maxMetersFromSource(user.maxDistanceFromSource().meters())
        .targetProduct(
            TriggerTargetProductRecord.builder()
                .typeId(targetProduct.type().id())
                .cents(targetProduct.price().cents())
                .build())
        .build();
  }
}
