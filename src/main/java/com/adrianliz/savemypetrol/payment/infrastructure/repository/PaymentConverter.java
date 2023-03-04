package com.adrianliz.savemypetrol.payment.infrastructure.repository;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentId;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscription;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionCancelDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionEndDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionStartDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentUser;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.adrianliz.savemypetrol.payment.infrastructure.repository.record.PaymentRecord;
import java.util.UUID;

public final class PaymentConverter {
  public static PaymentRecord toRecord(final Payment payment) {
    return PaymentRecord.builder()
        .id(UUID.fromString(payment.id().value()))
        .userId(payment.user().id().value())
        .startDate(payment.subscription().startDate().value())
        .endDate(payment.subscription().endDate().value())
        .cancelDate(payment.subscription().hasCancelDate() ? payment.subscription().cancelDate().value() : null)
        .build();
  }

  public static Payment toDomain(final PaymentRecord paymentRecord) {
    final var cancelDate = paymentRecord.getCancelDate();
    final var paymentSubscription = new PaymentSubscription(
        new PaymentSubscriptionStartDate(paymentRecord.getStartDate()),
        new PaymentSubscriptionEndDate(paymentRecord.getEndDate())
    );

    return new Payment(
        new PaymentId(paymentRecord.getId().toString()),
        new PaymentUser(new PaymentUserId(paymentRecord.getUserId())),
        cancelDate != null ? paymentSubscription.cancel(new PaymentSubscriptionCancelDate(paymentRecord.getCancelDate()))
            : paymentSubscription);
  }
}
