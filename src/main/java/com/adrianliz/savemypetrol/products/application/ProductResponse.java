package com.adrianliz.savemypetrol.products.application;

import com.adrianliz.savemypetrol.products.domain.Product;
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
        product.sellerPetrolStationId().getValue(), product.type().name(), product.price().cents());
  }
}
