package com.adrianliz.savemypetrol.match.infrastructure.repository.record;

import com.adrianliz.savemypetrol.match.domain.Match;
import com.adrianliz.savemypetrol.match.domain.PetrolStationMatch;
import com.adrianliz.savemypetrol.match.domain.UserMatch;
import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionId;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionUserId;
import lombok.Data;

@Data
public final class MatchRecord {

  private final String id;
  private final Long userId;
  private final ProductMatchRecord targetProduct;

  public Match buildSubscriptionMatch(final PetrolStation petrolStation) {
    return new Match(
        new SubscriptionId(id),
        new UserMatch(new SubscriptionUserId(userId)),
        new PetrolStationMatch(
            petrolStation.id(),
            petrolStation.products().stream()
                .filter(product -> product.type().id().equals(targetProduct.getTypeId()))
                .findFirst()
                .orElse(null)));
  }
}
