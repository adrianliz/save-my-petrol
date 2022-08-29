package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.products.domain.ProductPrice;
import com.adrianliz.savemypetrol.products.domain.ProductType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PetrolStationProduct implements Serializable {
  private final ProductType type;
  private final ProductPrice price;
}
