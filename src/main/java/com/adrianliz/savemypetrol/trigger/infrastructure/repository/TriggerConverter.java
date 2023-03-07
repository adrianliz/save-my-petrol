package com.adrianliz.savemypetrol.trigger.infrastructure.repository;

import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetProduct;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUser;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerRecord;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerTargetProductRecord;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public final class TriggerConverter {

  public static TriggerRecord toRecord(
      final com.adrianliz.savemypetrol.trigger.domain.Trigger trigger) {
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
