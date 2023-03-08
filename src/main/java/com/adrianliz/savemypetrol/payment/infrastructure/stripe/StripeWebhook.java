package com.adrianliz.savemypetrol.payment.infrastructure.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.Subscription;
import com.stripe.net.Webhook;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
public final class StripeWebhook {

  @Value("${app.stripe.webhookSecret}")
  private final String webhookSecret;

  private final NewSubscriptionCycleHandler newSubscriptionCycleHandler;
  private final SubscriptionDeletedHandler subscriptionDeletedHandler;

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
          return newSubscriptionCycleHandler
              .handle(newSubscription)
              .map(unused -> "New subscription cycle handled.");
        }
        case "customer.subscription.deleted" -> {
          final var deletedSubscription = (Subscription) eventBody;
          return subscriptionDeletedHandler
              .handle(deletedSubscription)
              .map(unused -> "Subscription deleted handled.");
        }
        default -> {
          return Mono.just("None action executed.");
        }
      }
      return Mono.just("None action executed.");
    } catch (final StripeException ex) {
      log.error("Error handling Stripe webhook", ex);
      return Mono.error(ex);
    }
  }
}
