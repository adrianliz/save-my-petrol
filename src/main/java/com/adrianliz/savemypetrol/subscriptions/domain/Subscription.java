package com.adrianliz.savemypetrol.subscriptions.domain;

import java.util.List;

public final class Subscription {
  private final SubscriptionId id;
  private final SubscriptionUser user;
  private final List<SubscriptionProduct> products;

  public Subscription(
      final SubscriptionId id,
      final SubscriptionUser user,
      final List<SubscriptionProduct> products) {
    this.id = id;
    this.user = user;
    this.products = products;
  }

  public SubscriptionId id() {
    return id;
  }

  public SubscriptionUser user() {
    return user;
  }

  public List<SubscriptionProduct> products() {
    return products;
  }
}
