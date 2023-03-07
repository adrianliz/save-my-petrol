package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTriggerTargetProduct;
import java.io.Serializable;
import java.util.Objects;

public final class TriggerTargetProduct implements Serializable {

  private final ProductType type;
  private final TriggerTargetProductPrice price;

  public TriggerTargetProduct(final ProductType type, final TriggerTargetProductPrice price) {
    validate(type, price);
    this.type = type;
    this.price = price;
  }

  private void validate(final ProductType type, final TriggerTargetProductPrice price) {
    if (type == null || price == null) {
      throw new InvalidTriggerTargetProduct();
    }
  }

  public ProductType type() {
    return type;
  }

  public TriggerTargetProductPrice price() {
    return price;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final TriggerTargetProduct that = (TriggerTargetProduct) o;
    return type == that.type && Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, price);
  }
}
