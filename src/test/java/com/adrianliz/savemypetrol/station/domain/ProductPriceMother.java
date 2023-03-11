package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.common.domain.Currency;
import com.adrianliz.savemypetrol.common.domain.LongMother;
import com.adrianliz.savemypetrol.common.domain.RandomElementPicker;
import com.adrianliz.savemypetrol.product.domain.ProductPrice;

public final class ProductPriceMother {

  public static ProductPrice create(final Long cents, final Currency currency) {
    return new ProductPrice(cents, currency);
  }

  public static ProductPrice random() {
    long cents = LongMother.random();
    do {
      cents = LongMother.random();
    } while (cents <= 0);
    return create(cents, RandomElementPicker.random(Currency.values()));
  }
}
