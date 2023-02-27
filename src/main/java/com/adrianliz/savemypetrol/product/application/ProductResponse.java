package com.adrianliz.savemypetrol.product.application;

import com.adrianliz.savemypetrol.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductResponse {

  private final Long sellerPetrolStationId;
  private final String type;
  private final Long cents;

  public static ProductResponse from(final Product product) {
    return new ProductResponse(
        product.sellerPetrolStationId().value(), product.type().name(), product.price().cents());
  }
}
