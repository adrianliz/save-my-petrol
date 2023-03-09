package com.adrianliz.savemypetrol.product.domain;

import com.adrianliz.savemypetrol.product.domain.exception.InvalidProduct;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Product implements Comparable<Product>, Serializable {

  private final PetrolStationId sellerPetrolStationId;
  private final ProductType type;
  private final ProductPrice price;

  public Product(
      final PetrolStationId sellerPetrolStationId,
      final ProductType type,
      final ProductPrice price) {

    validate(sellerPetrolStationId, type, price);
    this.sellerPetrolStationId = sellerPetrolStationId;
    this.type = type;
    this.price = price;
  }

  private void validate(
      final PetrolStationId sellerPetrolStationId,
      final ProductType type,
      final ProductPrice price) {

    if (sellerPetrolStationId == null || type == null || price == null) {
      throw new InvalidProduct();
    }
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
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Product product = (Product) o;
    return Objects.equals(sellerPetrolStationId, product.sellerPetrolStationId)
        && type == product.type
        && Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sellerPetrolStationId, type, price);
  }

  @Override
  public int compareTo(final Product product) {
    return Comparator.comparing(
            ProductPrice::cents, Comparator.nullsLast(Comparator.naturalOrder()))
        .compare(price, product.price);
  }
}
