package com.adrianliz.savemypetrol.match.application;

import com.adrianliz.savemypetrol.match.domain.Match;
import com.adrianliz.savemypetrol.match.domain.PetrolStationMatch;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class MatchResponse {

  private final String triggerId;
  private final Long userId;
  private final PetrolStationMatchResponse petrolStation;
  private final ProductMatchResponse product;

  public static MatchResponse from(final Match match) {
    final PetrolStationMatch petrolStationMatch = match.petrolStation();
    final PetrolStationLocation petrolStationLocation = petrolStationMatch.location();
    final PetrolStationProduct petrolStationProduct = petrolStationMatch.product();

    return new MatchResponse(
        match.id().valueAsString(),
        match.user().id().value(),
        new PetrolStationMatchResponse(
            petrolStationMatch.id().value(),
            petrolStationMatch.name().value(),
            petrolStationLocation.address(),
            petrolStationLocation.latitude(),
            petrolStationLocation.longitude()),
        new ProductMatchResponse(
            petrolStationProduct.type().id(),
            petrolStationProduct.type().label(),
            petrolStationProduct.price().cents()));
  }

  @AllArgsConstructor
  @Getter
  private static final class ProductMatchResponse {
    private final Integer id;
    private final String label;
    private final Long cents;
  }

  @AllArgsConstructor
  @Getter
  private static final class PetrolStationMatchResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final Double latitude;
    private final Double longitude;
  }
}
