package com.adrianliz.savemypetrol.subscriptions.domain;

import com.adrianliz.savemypetrol.products.domain.ProductType;

public final class SubscriptionProduct {
  private final ProductType type;
  private final SubscriptionProductPrice targetPrice;

  public SubscriptionProduct(final ProductType type, final SubscriptionProductPrice targetPrice) {
    this.type = type;
    this.targetPrice = targetPrice;
  }

  public ProductType type() {
    return type;
  }

  public SubscriptionProductPrice targetPrice() {
    return targetPrice;
  }
}
