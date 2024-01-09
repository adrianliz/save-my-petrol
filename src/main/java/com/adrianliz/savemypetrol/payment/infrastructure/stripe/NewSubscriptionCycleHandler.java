package com.adrianliz.savemypetrol.payment.infrastructure.stripe;

import static java.time.ZoneOffset.UTC;

import com.adrianliz.savemypetrol.payment.application.FindActivePaymentService;
import com.adrianliz.savemypetrol.payment.application.UpdatePaymentUseCase;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscription;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionEndDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionStartDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@Service
@AllArgsConstructor
public final class NewSubscriptionCycleHandler {
  private final FindActivePaymentService findActivePaymentService;
  private final UpdatePaymentUseCase updatePaymentUseCase;

  public Mono<StripeWebhookResult> handle(final Subscription newSubscription)
      throws StripeException {

    final var userId =
        Long.valueOf(
            Customer.retrieve(newSubscription.getCustomer()).getMetadata().get("telegram_user_id"));
    final var startDate =
        new PaymentSubscriptionStartDate(
            LocalDateTime.ofInstant(
                Instant.ofEpochSecond(newSubscription.getCurrentPeriodStart()), UTC));
    final var endDate =
        new PaymentSubscriptionEndDate(
            LocalDateTime.ofInstant(
                Instant.ofEpochSecond(newSubscription.getCurrentPeriodEnd()), UTC));
    final var paymentSubscription = new PaymentSubscription(startDate, endDate);

    return findActivePaymentService
        .execute(new PaymentUserId(userId))
        .flatMap(payment -> updatePaymentUseCase.execute(payment.renew(paymentSubscription)))
        .then(
            Mono.fromCallable(
                () ->
                    new StripeWebhookResult(
                        "New subscription cycle handled (user="
                            + userId
                            + "startDate="
                            + startDate
                            + "endDate="
                            + endDate
                            + ")")));
  }
}
