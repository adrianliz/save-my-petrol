package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.RandomElementPicker;
import com.adrianliz.savemypetrol.product.domain.ProductType;

public final class TriggerTargetProductMother {

  public static TriggerTargetProduct create(
      final ProductType type, final TriggerTargetProductPrice price) {
    return new TriggerTargetProduct(type, price);
  }

  public static TriggerTargetProduct random() {
    return create(
        RandomElementPicker.random(ProductType.values()), TriggerTargetProductPriceMother.random());
  }
}
