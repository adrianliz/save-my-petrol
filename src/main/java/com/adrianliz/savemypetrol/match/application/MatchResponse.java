package com.adrianliz.savemypetrol.match.application;

import com.adrianliz.savemypetrol.match.domain.Match;
import com.adrianliz.savemypetrol.match.domain.PetrolStationMatch;
import com.adrianliz.savemypetrol.station.domain.PetrolStationProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class MatchResponse {

  private final String subscriptionId;
  private final Long userId;
  private final Long petrolStationId;
  private final TargetProductResponse targetProduct;

  public static MatchResponse from(final Match match) {
    final PetrolStationMatch petrolStationMatch = match.petrolStation();
    final PetrolStationProduct petrolStationProduct = petrolStationMatch.product();

    return new MatchResponse(
        match.id().valueAsString(),
        match.user().id().value(),
        petrolStationMatch.id().value(),
        new TargetProductResponse(
            petrolStationProduct.type().name(), petrolStationProduct.price().cents()));
  }

  @AllArgsConstructor
  @Getter
  private static final class TargetProductResponse {

    private final String type;
    private final Long price;
  }
}
