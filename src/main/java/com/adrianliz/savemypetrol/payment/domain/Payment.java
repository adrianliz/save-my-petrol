package com.adrianliz.savemypetrol.payment.domain;

import java.io.Serializable;

public final class Payment implements Serializable {
  private final PaymentId id;
  private final PaymentUser user;
  private final PaymentSubscription subscription;

  public Payment(final PaymentId id, final PaymentUser user,
                 final PaymentSubscription subscription) {
    this.id = id;
    this.user = user;
    this.subscription = subscription;
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
}
