package com.adrianliz.savemypetrol.payment.infrastructure.repository;

import static java.time.ZoneOffset.UTC;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentId;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscription;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionCancelDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionEndDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionStartDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentUser;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.adrianliz.savemypetrol.payment.infrastructure.repository.record.PaymentRecord;
import java.time.LocalDateTime;

public final class PaymentConverter {

  public static PaymentRecord toRecord(final Payment payment) {
    return PaymentRecord.builder()
        .id(payment.id().value())
        .userId(payment.user().id().value())
        .startTimestamp(payment.subscription().startDate().value().atZone(UTC).toEpochSecond())
        .endTimestamp(payment.subscription().endDate().value().atZone(UTC).toEpochSecond())
        .cancelTimestamp(
            payment.subscription().isCancelled()
                ? payment.subscription().cancelDate().value().atZone(UTC).toEpochSecond()
                : null)
        .build();
  }

  public static Payment toEntity(final PaymentRecord paymentRecord) {
    final var cancelTimestamp = paymentRecord.getCancelTimestamp();
    final var paymentSubscription = new PaymentSubscription(
        new PaymentSubscriptionStartDate(
            LocalDateTime.ofEpochSecond(paymentRecord.getStartTimestamp(), 0, UTC)),
        new PaymentSubscriptionEndDate(
            LocalDateTime.ofEpochSecond(paymentRecord.getEndTimestamp(), 0, UTC))
    );

    return new Payment(
        new PaymentId(paymentRecord.getId().toString()),
        new PaymentUser(new PaymentUserId(paymentRecord.getUserId())),
        cancelTimestamp != null ? paymentSubscription.cancel(
            new PaymentSubscriptionCancelDate(
                LocalDateTime.ofEpochSecond(cancelTimestamp, 0, UTC)))
            : paymentSubscription);
  }
}
