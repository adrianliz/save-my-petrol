package com.adrianliz.savemypetrol.products.domain;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product implements Comparable<Product> {
  private final PetrolStationId sellerPetrolStationId;
  private final ProductType type;
  private final ProductPrice price;

  public String getName() {
    return type.getName();
  }

  public Long getCents() {
    return price.getCents();
  }

  @Override
  public int compareTo(final Product product) {
    return Comparator.comparing(Product::getCents, Comparator.nullsLast(Comparator.naturalOrder()))
        .compare(this, product);
  }
}
