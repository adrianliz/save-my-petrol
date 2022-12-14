package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.Currency;
import com.adrianliz.savemypetrol.common.domain.LongMother;
import com.adrianliz.savemypetrol.common.domain.RandomElementPicker;
import com.adrianliz.savemypetrol.products.domain.ProductPrice;

public final class ProductPriceMother {
  public static ProductPrice create(final Long cents, final Currency currency) {
    return new ProductPrice(cents, currency);
  }

  public static ProductPrice random() {
    return create(LongMother.random(), RandomElementPicker.random(Currency.values()));
  }
}
