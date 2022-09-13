package com.adrianliz.savemypetrol.stations.application;

import com.adrianliz.savemypetrol.stations.domain.*;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PetrolStationResponse {

  private final Long id;
  private final String name;
  private final PetrolStationLocationResponse location;
  private final List<PetrolStationProductResponse> products;

  private PetrolStationResponse(
      final PetrolStationId id,
      final PetrolStationName name,
      final PetrolStationLocation location,
      final List<PetrolStationProduct> products) {

    this.id = id.getValue();
    this.name = name.getValue();
    this.location = PetrolStationLocationResponse.from(location);
    this.products =
        products.stream().map(PetrolStationProductResponse::from).collect(Collectors.toList());
  }

  public static PetrolStationResponse from(final PetrolStation petrolStation) {
    return new PetrolStationResponse(
        petrolStation.id(),
        petrolStation.name(),
        petrolStation.location(),
        petrolStation.products());
  }

  @AllArgsConstructor
  @Getter
  private static class PetrolStationLocationResponse {
    private final Double latitude;
    private final Double longitude;
    private final String address;

    public static PetrolStationLocationResponse from(
        final PetrolStationLocation petrolStationLocation) {

      return new PetrolStationLocationResponse(
          petrolStationLocation.getLatitude(),
          petrolStationLocation.getLongitude(),
          petrolStationLocation.address());
    }
  }

  @AllArgsConstructor
  @Getter
  private static class PetrolStationProductResponse {
    private final String type;
    private final Long cents;

    public static PetrolStationProductResponse from(
        final PetrolStationProduct petrolStationProduct) {

      return new PetrolStationProductResponse(
          petrolStationProduct.type().name(), petrolStationProduct.price().cents());
    }
  }
}
