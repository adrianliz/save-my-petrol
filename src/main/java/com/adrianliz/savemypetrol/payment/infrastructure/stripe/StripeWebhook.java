package com.adrianliz.savemypetrol.payment.infrastructure.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.Subscription;
import com.stripe.net.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@RestController
@Slf4j
public final class StripeWebhook {

  private final String webhookSecret;
  private final NewSubscriptionCycleHandler newSubscriptionCycleHandler;
  private final SubscriptionDeletedHandler subscriptionDeletedHandler;

  public StripeWebhook(
      @Value("${app.stripe.webhookSecret}") final String webhookSecret,
      final NewSubscriptionCycleHandler newSubscriptionCycleHandler,
      final SubscriptionDeletedHandler subscriptionDeletedHandler) {
    this.webhookSecret = webhookSecret;
    this.newSubscriptionCycleHandler = newSubscriptionCycleHandler;
    this.subscriptionDeletedHandler = subscriptionDeletedHandler;
  }

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
              .map(StripeWebhookResult::message);
        }
        case "customer.subscription.deleted" -> {
          final var deletedSubscription = (Subscription) eventBody;
          return subscriptionDeletedHandler
              .handle(deletedSubscription)
              .map(StripeWebhookResult::message);
        }
        default -> {
          return Mono.just("None action executed.");
        }
      }
      return Mono.just("None action executed.");
    } catch (final StripeException ex) {
      log.error("StripeWebhook> Error when handling Stripe event", ex);
      return Mono.error(ex);
    }
  }
}
