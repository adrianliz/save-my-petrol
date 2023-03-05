package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.product.domain.ProductPrice;
import com.adrianliz.savemypetrol.product.domain.ProductType;
import java.io.Serializable;
import java.util.Objects;

public class PetrolStationProduct implements Serializable {

  private final ProductType type;
  private final ProductPrice price;

  public PetrolStationProduct(final ProductType type, final ProductPrice price) {
    this.type = type;
    this.price = price;
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
    final PetrolStationProduct that = (PetrolStationProduct) o;
    return type == that.type && Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, price);
  }
}
