package com.adrianliz.savemypetrol.payment.infrastructure.stripe;

import static java.time.ZoneOffset.UTC;

import com.adrianliz.savemypetrol.payment.application.FindActivePaymentService;
import com.adrianliz.savemypetrol.payment.application.UpdatePaymentUseCase;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionCancelDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class SubscriptionDeletedHandler {
  private final FindActivePaymentService findActivePaymentService;
  private final UpdatePaymentUseCase updatePaymentUseCase;

  public Mono<StripeWebhookResult> handle(final Subscription deletedSubscription)
      throws StripeException {

    final var userIdToDelete =
        Long.valueOf(
            Customer.retrieve(deletedSubscription.getCustomer())
                .getMetadata()
                .get("telegram_user_id"));
    final var cancelDate =
        new PaymentSubscriptionCancelDate(
            LocalDateTime.ofInstant(
                Instant.ofEpochSecond(deletedSubscription.getCanceledAt()), UTC));

    return findActivePaymentService
        .execute(new PaymentUserId(userIdToDelete))
        .flatMap(payment -> updatePaymentUseCase.execute(payment.cancel(cancelDate)))
        .then(
            Mono.fromCallable(
                () ->
                    new StripeWebhookResult(
                        "Subscription deleted handled (user="
                            + userIdToDelete
                            + "cancelDate="
                            + cancelDate
                            + ")")));
  }
}
