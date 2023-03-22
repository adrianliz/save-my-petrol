package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.LongMother;

public final class TriggerTargetProductPriceMother {

  public static TriggerTargetProductPrice create(final Long value) {
    return new TriggerTargetProductPrice(value);
  }

  public static TriggerTargetProductPrice random() {
    long price;
    do {
      price = LongMother.random();
    } while (price <= 0);
    return create(price);
  }
}
