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
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PaymentLinkCreateParams.AfterCompletion;
import com.stripe.param.PaymentLinkCreateParams.AfterCompletion.Redirect;
import com.stripe.param.PaymentLinkCreateParams.LineItem;
import com.stripe.param.PaymentLinkUpdateParams;
import com.stripe.param.ProductListParams;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public final class StripeService implements PaymentPageGenerator {

  private final String successPaymentUrl;

  public StripeService(
      @Value("${app.stripe.apiKey}") final String apiKey,
      @Value("${app.domain}") final String domain) {

    Stripe.apiKey = apiKey;
    successPaymentUrl = UriComponentsBuilder.fromHttpUrl(domain)
        .path("/api/v1/payments/success-page").queryParam("session_id", "{CHECKOUT_SESSION_ID}")
        .build().toUriString();
  }

  public Optional<Session> getCheckoutSession(final String sessionId) {
    try {
      return Optional.ofNullable(Session.retrieve(sessionId));
    } catch (final StripeException ex) {
      return Optional.empty();
    }
  }

  public Optional<Subscription> getSubscription(final String subscriptionId) {
    try {
      return Optional.ofNullable(Subscription.retrieve(subscriptionId));
    } catch (final StripeException ex) {
      return Optional.empty();
    }
  }

  public void deactivatePaymentLink(final Session session) {
    try {
      PaymentLink.retrieve(session.getPaymentLink())
          .update(PaymentLinkUpdateParams.builder().setActive(false).build());
    } catch (final StripeException e) {
      // Ignore
    }
  }

  public void associateInternalUser(final Session session) {
    try {
      Customer.retrieve(Subscription.retrieve(session.getSubscription()).getCustomer())
          .update(
              CustomerUpdateParams.builder()
                  .putMetadata("telegram_user_id", session.getMetadata().get("telegram_user_id"))
                  .build());
    } catch (final StripeException e) {
      // Ignore
    }
  }

  @Override
  public Optional<PaymentPage> generate(final PaymentUserId paymentUserId) {
    try {
      final var item = Product.list(ProductListParams.builder().setActive(true).build()).getData()
          .stream().filter(product -> "Save My Petrol Notifications".equals(product.getName()))
          .findFirst()
          .orElseThrow();

      return Optional.of(
          new PaymentPage(
              PaymentLink.create(
                      PaymentLinkCreateParams.builder()
                          .addAllLineItem(List.of(LineItem.builder()
                              .setPrice(item.getDefaultPrice())
                              .setQuantity(1L)
                              .build()))
                          .setAfterCompletion(
                              AfterCompletion.builder()
                                  .setType(AfterCompletion.Type.REDIRECT)
                                  .setRedirect(Redirect.builder()
                                      .setUrl(successPaymentUrl).build())
                                  .build())
                          .putMetadata("telegram_user_id", paymentUserId.value().toString())
                          .build())
                  .getUrl()));
    } catch (final StripeException ex) {
      return Optional.empty();
    }
  }
}
