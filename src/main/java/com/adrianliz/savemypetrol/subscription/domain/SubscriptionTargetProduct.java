package com.adrianliz.savemypetrol.subscription.domain;

import com.adrianliz.savemypetrol.product.domain.ProductType;
import java.io.Serializable;
import java.util.Objects;

public final class SubscriptionTargetProduct implements Serializable {
  private final ProductType type;
  private final SubscriptionTriggerProductPrice triggerPrice;

  public SubscriptionTargetProduct(
      final ProductType type, final SubscriptionTriggerProductPrice triggerPrice) {
    this.type = type;
    this.triggerPrice = triggerPrice;
  }

  public ProductType type() {
    return type;
  }

  public SubscriptionTriggerProductPrice triggerPrice() {
    return triggerPrice;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SubscriptionTargetProduct that = (SubscriptionTargetProduct) o;
    return type == that.type && Objects.equals(triggerPrice, that.triggerPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, triggerPrice);
  }
}
