package com.adrianliz.savemypetrol.payment.domain;

import java.io.Serializable;

public final class PaymentSubscription implements Serializable {
  private final PaymentSubscriptionStartDate startDate;
  private final PaymentSubscriptionEndDate endDate;
  private final PaymentSubscriptionCancelDate cancelDate;

  private PaymentSubscription(final PaymentSubscriptionStartDate startDate, final PaymentSubscriptionEndDate endDate, final PaymentSubscriptionCancelDate cancelDate) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.cancelDate = cancelDate;
  }

  public PaymentSubscription(final PaymentSubscriptionStartDate startDate, final PaymentSubscriptionEndDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
    cancelDate = null;
  }

  public boolean hasCancelDate() {
    return cancelDate != null;
  }

  public PaymentSubscription cancel(final PaymentSubscriptionCancelDate cancelDate) {
    if (hasCancelDate() || cancelDate == null || endDate.isAfter(cancelDate)) {
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

  private void validate(
      final PaymentSubscriptionStartDate startDate, final PaymentSubscriptionEndDate endDate) {
    if (startDate == null || endDate == null || !startDate.isBefore(endDate)) {
      throw new InvalidPaymentSubscription();
    }
  }
}
