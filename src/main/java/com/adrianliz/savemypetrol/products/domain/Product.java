package com.adrianliz.savemypetrol.products.domain;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import java.util.Comparator;

public class Product implements Comparable<Product> {
  private final PetrolStationId sellerPetrolStationId;
  private final ProductType type;
  private final ProductPrice price;

  public Product(
      final PetrolStationId sellerPetrolStationId,
      final ProductType type,
      final ProductPrice price) {

    this.sellerPetrolStationId = sellerPetrolStationId;
    this.type = type;
    this.price = price;
  }

  public boolean hasType(final ProductType type) {
    return this.type.equals(type);
  }

  public PetrolStationId sellerPetrolStationId() {
    return sellerPetrolStationId;
  }

  public ProductType type() {
    return type;
  }

  public ProductPrice price() {
    return price;
  }

  @Override
  public int compareTo(final Product product) {
    return Comparator.comparing(
            ProductPrice::cents, Comparator.nullsLast(Comparator.naturalOrder()))
        .compare(this.price, product.price);
  }
}
