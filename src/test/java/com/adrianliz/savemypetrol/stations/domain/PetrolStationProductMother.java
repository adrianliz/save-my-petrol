package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.ListMother;
import com.adrianliz.savemypetrol.common.domain.RandomElementPicker;
import com.adrianliz.savemypetrol.products.domain.ProductPrice;
import com.adrianliz.savemypetrol.products.domain.ProductType;
import java.util.List;

public final class PetrolStationProductMother {
  public static PetrolStationProduct create(final ProductType type, final ProductPrice price) {
    return new PetrolStationProduct(type, price);
  }

  public static PetrolStationProduct random() {
    return create(RandomElementPicker.random(ProductType.values()), ProductPriceMother.random());
  }

  public static List<PetrolStationProduct> randoms() {
    return ListMother.random(PetrolStationProductMother::random);
  }
}
