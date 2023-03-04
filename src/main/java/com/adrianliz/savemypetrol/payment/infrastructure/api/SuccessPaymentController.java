package com.adrianliz.savemypetrol.payment.infrastructure.api;

import static java.time.ZoneOffset.UTC;

import com.adrianliz.savemypetrol.payment.application.RegisterPaymentUseCase;
import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentId;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscription;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionEndDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionStartDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentUser;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.adrianliz.savemypetrol.payment.infrastructure.web.StripeService;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class SuccessPaymentController {
  private final RegisterPaymentUseCase registerPaymentUseCase;
  private final StripeService stripeService;

  @Value("classpath:success-page.html")
  private final Resource successPage;

  @GetMapping("/payments/success-page")
  public Flux<DataBuffer> getSuccessPayment(@RequestParam(value = "session_id") final String sessionId) {
    final var session = stripeService.getCheckoutSession(sessionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    return Mono.just(buildPayment(session))
        .doOnSuccess(registerPaymentUseCase::execute)
        .doOnSuccess(payment -> stripeService.deactivatePaymentLink(session))
        .retryWhen(Retry.backoff(10, Duration.ofSeconds(1)))
        .thenMany(DataBufferUtils.read(successPage, new DefaultDataBufferFactory(), 4096));

  }

  private Payment buildPayment(final Session session) {
    final Subscription subscription = stripeService.getSubscription(session.getSubscription()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return new Payment(new PaymentId(UUID.randomUUID().toString()),
        new PaymentUser(new PaymentUserId(Long.valueOf(session.getMetadata().get("telegram_user_id")))),
        new PaymentSubscription(
            new PaymentSubscriptionStartDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(subscription.getStartDate()), UTC)),
            new PaymentSubscriptionEndDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(subscription.getCurrentPeriodEnd()), UTC))));
  }
}
