package com.adrianliz.savemypetrol.payment.infrastructure.web;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentLinkUpdateParams;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public final class StripeService {
  public StripeService(@Value("${app.stripe.apiKey}") final String apiKey) {
    Stripe.apiKey = apiKey;
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
      PaymentLink.retrieve(session.getPaymentLink()).update(PaymentLinkUpdateParams.builder()
          .setActive(false)
          .build());
    } catch (final StripeException e) {
      // Ignore
    }
  }

}
