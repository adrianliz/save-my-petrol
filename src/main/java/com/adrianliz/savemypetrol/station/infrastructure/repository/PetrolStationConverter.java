package com.adrianliz.savemypetrol.station.infrastructure.repository;

import com.adrianliz.savemypetrol.common.domain.Currency;
import com.adrianliz.savemypetrol.product.domain.ProductPrice;
import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationName;
import com.adrianliz.savemypetrol.station.domain.PetrolStationProduct;
import com.adrianliz.savemypetrol.station.infrastructure.repository.record.PetrolStationProductRecord;
import com.adrianliz.savemypetrol.station.infrastructure.repository.record.PetrolStationRecord;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public final class PetrolStationConverter {

  public static PetrolStation toEntity(final PetrolStationRecord record) {
    final var id = new PetrolStationId(record.getId());
    final var name = new PetrolStationName(record.getName());
    final var location =
        new PetrolStationLocation(
            record.getLocation().getY(), record.getLocation().getX(), record.getAddress());
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
    recordBuilder.id(petrolStation.id().value());
    recordBuilder.name(petrolStation.name().value());

    final var location = petrolStation.location();
    recordBuilder.address(location.address());
    recordBuilder.location(new GeoJsonPoint(location.longitude(), location.latitude()));

    recordBuilder.products(
        petrolStation.products().stream()
            .map(
                product ->
                    PetrolStationProductRecord.builder()
                        .typeId(product.type().id())
                        .cents(product.price().cents())
                        .currency(product.price().currency().symbolName())
                        .build())
            .toList());

    return recordBuilder.build();
  }
}
