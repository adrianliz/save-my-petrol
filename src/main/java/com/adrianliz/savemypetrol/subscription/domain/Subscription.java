package com.adrianliz.savemypetrol.subscription.domain;

import java.io.Serializable;
import java.util.Objects;

public final class Subscription implements Serializable {

  private final SubscriptionId id;
  private final SubscriptionUser user;
  private final SubscriptionTargetProduct targetProduct;

  public Subscription(
      final SubscriptionId id,
      final SubscriptionUser user,
      final SubscriptionTargetProduct targetProduct) {
    this.id = id;
    this.user = user;
    this.targetProduct = targetProduct;
  }

  public SubscriptionId id() {
    return id;
  }

  public SubscriptionUser user() {
    return user;
  }

  public SubscriptionTargetProduct targetProduct() {
    return targetProduct;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Subscription that = (Subscription) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
