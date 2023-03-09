package com.adrianliz.savemypetrol.match.infrastructure.repository.record;

import com.adrianliz.savemypetrol.match.domain.Match;
import com.adrianliz.savemypetrol.match.domain.PetrolStationMatch;
import com.adrianliz.savemypetrol.match.domain.UserMatch;
import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.trigger.domain.TriggerId;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerTargetProductRecord;
import lombok.Data;

@Data
public final class MatchRecord {

  private final String id;
  private final Long userId;
  private final TriggerTargetProductRecord targetProduct;

  public Match buildSubscriptionMatch(final PetrolStation petrolStation) {
    return new Match(
        new TriggerId(id),
        new UserMatch(new TriggerTargetUserId(userId)),
        new PetrolStationMatch(
            petrolStation.id(),
            petrolStation.name(),
            petrolStation.location(),
            petrolStation.products().stream()
                .filter(product -> product.type().id().equals(targetProduct.getTypeId()))
                .findFirst()
                .orElse(null)));
  }
}
