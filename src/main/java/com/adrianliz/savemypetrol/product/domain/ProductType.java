package com.adrianliz.savemypetrol.product.domain;

import java.io.Serializable;
import java.util.Arrays;

public enum ProductType implements Serializable {
  REGULAR_95(1, "Gasolina 95 E5"),
  REGULAR_95_PREMIUM(20, "Gasolina 95 E5 Premium"),
  REGULAR_98(3, "Gasolina 98 E5"),
  DIESEL_A(4, "Gasóleo A habitual"),
  DIESEL_A_PREMIUM(5, "Gasóleo Premium"),
  DIESEL_B(6, "Gasóleo B");

  private final Integer id;
  private final String name;

  ProductType(final Integer id, final String name) {
    this.id = id;
    this.name = name;
  }

  public static ProductType findById(final Integer id) {
    return Arrays.stream(ProductType.values())
        .filter(productType -> productType.id.equals(id))
        .findFirst()
        .orElse(null);
  }

  public Integer id() {
    return id;
  }
}
