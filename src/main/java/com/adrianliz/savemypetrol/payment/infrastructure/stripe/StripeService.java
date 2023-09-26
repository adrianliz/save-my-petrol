package com.adrianliz.savemypetrol.payment.infrastructure.stripe;

import com.adrianliz.savemypetrol.payment.domain.PaymentPage;
import com.adrianliz.savemypetrol.payment.domain.PaymentPageGenerator;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentLink;
import com.stripe.model.Product;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerSearchParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PaymentLinkCreateParams.AfterCompletion;
import com.stripe.param.PaymentLinkCreateParams.AfterCompletion.Redirect;
import com.stripe.param.PaymentLinkCreateParams.LineItem;
import com.stripe.param.PaymentLinkUpdateParams;
import com.stripe.param.ProductListParams;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public final class StripeService implements PaymentPageGenerator {

  private final String successPaymentUrl;

  public StripeService(
      @Value("${app.stripe.apiKey}") final String apiKey,
      @Value("${app.baseUrl}") final String appBaseUrl) {

    Stripe.apiKey = apiKey;
    Stripe.setMaxNetworkRetries(5);
    successPaymentUrl =
        UriComponentsBuilder.fromHttpUrl(appBaseUrl)
            .path("/api/v1/payments/success-page")
            .queryParam("session_id", "{CHECKOUT_SESSION_ID}")
            .build()
            .toUriString();
  }

  public Optional<Session> getCheckoutSession(final String sessionId) {
    try {
      return Optional.ofNullable(Session.retrieve(sessionId));
    } catch (final StripeException ex) {
      log.error("StripeService> Error when retrieving checkout session", ex);
      return Optional.empty();
    }
  }

  public Optional<Subscription> getSubscription(final String subscriptionId) {
    try {
      return Optional.ofNullable(Subscription.retrieve(subscriptionId));
    } catch (final StripeException ex) {
      log.error("StripeService> Error when retrieving subscription", ex);
      return Optional.empty();
    }
  }

  public void associateInternalUser(final Session session) {
    try {
      Customer.retrieve(Subscription.retrieve(session.getSubscription()).getCustomer())
          .update(
              CustomerUpdateParams.builder()
                  .putMetadata("telegram_user_id", session.getMetadata().get("telegram_user_id"))
                  .build());
    } catch (final StripeException ex) {
      log.error("StripeService> Error when associating internal user", ex);
    }
  }

  public void deactivatePaymentLink(final Session session) {
    try {
      PaymentLink.retrieve(session.getPaymentLink())
          .update(PaymentLinkUpdateParams.builder().setActive(false).build());
    } catch (final StripeException ex) {
      log.error("StripeService> Error when deactivating payment link", ex);
    }
  }

  public void unsubscribe(final Long userId) {
    try {
      Optional.ofNullable(
              Customer.search(
                  CustomerSearchParams.builder()
                      .setQuery("metadata['telegram_user_id']:'" + userId + "'")
                      .addExpand("data.subscriptions")
                      .build()))
          .orElseThrow(() -> new RuntimeException("Customer not found in Stripe"))
          .getData()
          .stream()
          .findFirst()
          .orElseThrow(() -> new RuntimeException("Customer not found in Stripe"))
          .getSubscriptions()
          .getData()
          .stream()
          .filter(subscription -> !subscription.getStatus().equals("canceled"))
          .forEach(
              subscription -> {
                try {
                  subscription.cancel();
                } catch (final StripeException ex) {
                  log.error("StripeService> Error when unsubscribing", ex);
                }
              });
    } catch (final StripeException ex) {
      log.error("StripeService> Error when unsubscribing", ex);
    }
  }

  @Override
  public Optional<PaymentPage> generate(final PaymentUserId paymentUserId) {
    try {
      final var monthlySubscriptionProduct =
          Product.list(ProductListParams.builder().setActive(true).build()).getData().stream()
              .filter(
                  product ->
                      product.getMetadata().entrySet().stream()
                          .filter(entry -> entry.getKey().equals("monthly_subscription"))
                          .findFirst()
                          .map(entry -> entry.getValue().equals("true"))
                          .orElse(false))
              .findFirst()
              .orElseThrow();

      return Optional.of(
          new PaymentPage(
              PaymentLink.create(
                      PaymentLinkCreateParams.builder()
                          .addAllLineItem(
                              List.of(
                                  LineItem.builder()
                                      .setPrice(monthlySubscriptionProduct.getDefaultPrice())
                                      .setQuantity(1L)
                                      .build()))
                          .setAfterCompletion(
                              AfterCompletion.builder()
                                  .setType(AfterCompletion.Type.REDIRECT)
                                  .setRedirect(Redirect.builder().setUrl(successPaymentUrl).build())
                                  .build())
                          .putMetadata("telegram_user_id", paymentUserId.value().toString())
                          .build())
                  .getUrl()));
    } catch (final StripeException ex) {
      log.error("StripeService> Error when generating payment page", ex);
      return Optional.empty();
    }
  }
}
