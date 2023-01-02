package com.adrianliz.savemypetrol.products.domain;

import com.adrianliz.savemypetrol.common.domain.Currency;

import java.io.Serializable;

public class ProductPrice implements Serializable {
  private final Long cents;
  private final Currency currency;

  public ProductPrice(final Long cents, final Currency currency) {
    validate(cents, currency);
    this.cents = cents;
    this.currency = currency;
  }

  private void validate(final Long cents, final Currency currency) {
    if (cents == null || currency == null || cents < 0) {
      throw new InvalidProductPrice();
    }
  }

  public Long cents() {
    return cents;
  }

  public Currency currency() {
    return currency;
  }
}
