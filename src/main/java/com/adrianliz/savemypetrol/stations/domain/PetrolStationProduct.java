package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.products.domain.ProductPrice;
import com.adrianliz.savemypetrol.products.domain.ProductType;
import java.io.Serializable;

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
}
