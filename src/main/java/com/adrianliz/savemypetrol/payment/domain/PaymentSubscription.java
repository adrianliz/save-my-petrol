package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.payment.domain.exception.InvalidPaymentSubscription;
import com.adrianliz.savemypetrol.payment.domain.exception.InvalidPaymentSubscriptionCancelDate;
import java.io.Serializable;
import java.util.Objects;

public final class PaymentSubscription implements Serializable {

  private final PaymentSubscriptionStartDate startDate;
  private final PaymentSubscriptionEndDate endDate;
  private final PaymentSubscriptionCancelDate cancelDate;

  private PaymentSubscription(
      final PaymentSubscriptionStartDate startDate,
      final PaymentSubscriptionEndDate endDate,
      final PaymentSubscriptionCancelDate cancelDate) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.cancelDate = cancelDate;
  }

  public PaymentSubscription(
      final PaymentSubscriptionStartDate startDate, final PaymentSubscriptionEndDate endDate) {

    validate(startDate, endDate);
    this.startDate = startDate;
    this.endDate = endDate;
    cancelDate = null;
  }

  private void validate(
      final PaymentSubscriptionStartDate startDate, final PaymentSubscriptionEndDate endDate) {

    if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
      throw new InvalidPaymentSubscription();
    }
  }

  public boolean isCancelled() {
    return cancelDate != null;
  }

  public boolean createdAfter(final PaymentSubscription subscription) {
    if (subscription == null) {
      throw new InvalidPaymentSubscription();
    }
    return startDate.isAfter(subscription.endDate);
  }

  public PaymentSubscription cancel(final PaymentSubscriptionCancelDate cancelDate) {
    if (isCancelled() || cancelDate == null || startDate.isAfter(cancelDate)) {
      throw new InvalidPaymentSubscriptionCancelDate();
    }
    return new PaymentSubscription(startDate, endDate, cancelDate);
  }

  public PaymentSubscriptionStartDate startDate() {
    return startDate;
  }

  public PaymentSubscriptionEndDate endDate() {
    return endDate;
  }

  public PaymentSubscriptionCancelDate cancelDate() {
    return cancelDate;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PaymentSubscription that = (PaymentSubscription) o;
    return Objects.equals(startDate, that.startDate)
        && Objects.equals(endDate, that.endDate)
        && Objects.equals(cancelDate, that.cancelDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, cancelDate);
  }
}
