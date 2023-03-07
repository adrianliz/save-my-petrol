package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.payment.domain.exception.InvalidPayment;
import com.adrianliz.savemypetrol.payment.domain.exception.InvalidPaymentSubscription;
import java.io.Serializable;
import java.util.Objects;

public final class Payment implements Serializable {

  private final PaymentId id;
  private final PaymentUser user;
  private final PaymentSubscription subscription;

  public Payment(
      final PaymentId id, final PaymentUser user, final PaymentSubscription subscription) {

    validate(id, user, subscription);
    this.id = id;
    this.user = user;
    this.subscription = subscription;
  }

  private void validate(
      final PaymentId id, final PaymentUser user, final PaymentSubscription subscription) {

    if (id == null || user == null || subscription == null) {
      throw new InvalidPayment();
    }
  }

  public Payment renew(final PaymentSubscription subscription) {
    if (subscription == null
        || this.subscription.isCancelled()
        || this.subscription.createdAfter(subscription)) {
      throw new InvalidPaymentSubscription();
    }
    return new Payment(id, user, subscription);
  }

  public Payment cancel(final PaymentSubscriptionCancelDate cancelDate) {
    return new Payment(id, user, subscription.cancel(cancelDate));
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
