package com.adrianliz.savemypetrol.payment.infrastructure.api;

import static java.time.ZoneOffset.UTC;

import com.adrianliz.savemypetrol.payment.application.FindActivePaymentService;
import com.adrianliz.savemypetrol.payment.application.UpdatePaymentUseCase;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscription;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionCancelDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionEndDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionStartDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Subscription;
import com.stripe.net.Webhook;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public final class StripeWebhook {

  @Value("${app.stripe.webhookSecret}")
  private final String webhookSecret;

  private final FindActivePaymentService findActivePaymentService;
  private final UpdatePaymentUseCase updatePaymentUseCase;

  @PostMapping("/stripe/webhook")
  public Mono<String> handle(
      @RequestBody final String payload,
      @RequestHeader("Stripe-Signature") final String signature) {

    try {
      final var event = Webhook.constructEvent(payload, signature, webhookSecret);
      final var eventBody = event.getDataObjectDeserializer().getObject().orElseThrow();

      switch (event.getType()) {
        case "invoice.paid" -> {
          final var invoice = (Invoice) eventBody;
          if (!"subscription_cycle".equals(invoice.getBillingReason())) {
            break;
          }
          final var newSubscription = Subscription.retrieve(invoice.getSubscription());
          final var userId =
              Long.valueOf(
                  Customer.retrieve(newSubscription.getCustomer())
                      .getMetadata()
                      .get("telegram_user_id"));
          final var paymentSubscription =
              new PaymentSubscription(
                  new PaymentSubscriptionStartDate(
                      LocalDateTime.ofInstant(
                          Instant.ofEpochSecond(newSubscription.getCurrentPeriodStart()), UTC)),
                  new PaymentSubscriptionEndDate(
                      LocalDateTime.ofInstant(
                          Instant.ofEpochSecond(newSubscription.getCurrentPeriodEnd()), UTC)));
          return findActivePaymentService
              .execute(new PaymentUserId(userId))
              .flatMap(payment -> updatePaymentUseCase.execute(payment.renew(paymentSubscription)))
              .map(unused -> "Success.");
        }
        case "customer.subscription.deleted" -> {
          final var subscription = (Subscription) eventBody;
          final var userIdToDelete =
              Long.valueOf(
                  Customer.retrieve(subscription.getCustomer())
                      .getMetadata()
                      .get("telegram_user_id"));
          final var cancelDate =
              new PaymentSubscriptionCancelDate(
                  LocalDateTime.ofInstant(
                      Instant.ofEpochSecond(subscription.getCanceledAt()), UTC));
          return findActivePaymentService
              .execute(new PaymentUserId(userIdToDelete))
              .flatMap(payment -> updatePaymentUseCase.execute(payment.cancel(cancelDate)))
              .map(unused -> "Success.");
        }
        default -> {
          return Mono.just("None action executed.");
        }
      }
      return Mono.just("None action executed.");
    } catch (final StripeException ex) {
      return Mono.error(ex);
    }
  }
}
