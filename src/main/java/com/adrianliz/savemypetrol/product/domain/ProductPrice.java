package com.adrianliz.savemypetrol.product.domain;

import com.adrianliz.savemypetrol.common.domain.Currency;
import java.io.Serializable;
import java.util.Objects;

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

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ProductPrice that = (ProductPrice) o;
    return Objects.equals(cents, that.cents) && currency == that.currency;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cents, currency);
  }
}
