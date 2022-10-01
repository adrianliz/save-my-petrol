package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.adrianliz.savemypetrol.common.domain.Currency;
import com.adrianliz.savemypetrol.products.domain.ProductPrice;
import com.adrianliz.savemypetrol.products.domain.ProductType;
import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationName;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationProduct;

public final class PetrolStationConverter {
  public static PetrolStation toEntity(final PetrolStationRecord record) {
    final var id = new PetrolStationId(record.getId());
    final var name = new PetrolStationName(record.getName());
    final var location =
        new PetrolStationLocation(record.getLatitude(), record.getLongitude(), record.getAddress());
    final var products =
        record.getProducts().stream()
            .map(
                productRecord ->
                    new PetrolStationProduct(
                        ProductType.findById(productRecord.getTypeId()),
                        new ProductPrice(productRecord.getCents(), Currency.EUR)))
            .toList();

    return new PetrolStation(id, name, location, products);
  }

  public static PetrolStationRecord toRecord(final PetrolStation petrolStation) {
    final var recordBuilder = PetrolStationRecord.builder();
    recordBuilder.id(petrolStation.id().getValue());
    recordBuilder.name(petrolStation.name().getValue());

    final var location = petrolStation.location();
    recordBuilder.address(location.address());
    recordBuilder.latitude(location.getLatitude());
    recordBuilder.longitude(location.getLongitude());

    recordBuilder.products(
        petrolStation.products().stream()
            .map(
                product ->
                    PetrolStationProductRecord.builder()
                        .typeId(product.type().id())
                        .cents(product.price().cents())
                        .currency(product.price().currency().getName())
                        .build())
            .toList());

    return recordBuilder.build();
  }
}
