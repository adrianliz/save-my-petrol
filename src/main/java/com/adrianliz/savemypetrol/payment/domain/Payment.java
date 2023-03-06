package com.adrianliz.savemypetrol.payment.domain;

import java.io.Serializable;
import java.util.Objects;

public final class Payment implements Serializable {

  private final PaymentId id;
  private final PaymentUser user;
  private final PaymentSubscription subscription;

  public Payment(
      final PaymentId id,
      final PaymentUser user,
      final PaymentSubscription subscription) {
    this.id = id;
    this.user = user;
    this.subscription = subscription;
  }

  public Payment withSubscription(final PaymentSubscription subscription) {
    if (this.subscription.isCancelled() || !subscription.isAfter(this.subscription)) {
      throw new InvalidPaymentSubscription();
    }
    return new Payment(id, user, subscription);
  }

  public PaymentId id() {
    return id;
  }

  public PaymentUser user() {
    return user;
  }

  public PaymentSubscription subscription() {
    return subscription;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Payment payment = (Payment) o;
    return Objects.equals(id, payment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
