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
import com.adrianliz.savemypetrol.payment.infrastructure.stripe.StripeService;
import com.stripe.model.Subscription;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
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
import reactor.util.retry.Retry;

@RestController
@Slf4j
public final class GetSuccessPaymentPageController implements PaymentsControllerV1 {

  private final StripeService stripeService;
  private final RegisterPaymentUseCase registerPaymentUseCase;
  private final Resource successPage;
  private final Resource errorPage;

  public GetSuccessPaymentPageController(
      final RegisterPaymentUseCase registerPaymentUseCase,
      final StripeService stripeService,
      @Value("classpath:public/success-page.html") final Resource successPage,
      @Value("classpath:public/error-page.html") final Resource errorPage) {
    this.registerPaymentUseCase = registerPaymentUseCase;
    this.stripeService = stripeService;
    this.successPage = successPage;
    this.errorPage = errorPage;
  }

  @GetMapping("/success-page")
  public Flux<DataBuffer> getSuccessPaymentPage(
      @RequestParam(value = "session_id") final String sessionId) {

    final var session =
        stripeService
            .getCheckoutSession(sessionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    final var subscription = stripeService.getSubscription(session.getSubscription());
    if (subscription.isEmpty()) {
      log.error(
          "GetSuccessPaymentPageController> Subscription not found for session {}", sessionId);
      return DataBufferUtils.read(errorPage, new DefaultDataBufferFactory(), 4096);
    }

    final var userId = session.getMetadata().get("telegram_user_id");
    try {
      final var payment = buildPayment(Long.valueOf(userId), subscription.get());

      return registerPaymentUseCase
          .execute(payment)
          .doOnSuccess(unused -> stripeService.associateInternalUser(session))
          .doOnSuccess(unused -> stripeService.deactivatePaymentLink(session))
          .retryWhen(
              Retry.backoff(5, Duration.ofSeconds(5))
                  .doAfterRetry(
                      retry ->
                          log.error(
                              "GetSuccessPaymentPageController> "
                                  + "Error registering payment (retry={})",
                              retry.totalRetries(),
                              retry.failure())))
          .thenMany(DataBufferUtils.read(successPage, new DefaultDataBufferFactory(), 4096))
          .onErrorResume(
              ex -> {
                log.error(
                    "GetSuccessPaymentPageController> "
                        + "Error registering payment (userId={}, session={}, subscription={})",
                    userId,
                    session.getId(),
                    subscription.map(Subscription::getId).orElse("unknown"),
                    ex);
                return DataBufferUtils.read(errorPage, new DefaultDataBufferFactory(), 4096);
              });
    } catch (final Exception ex) {
      log.error(
          "GetSuccessPaymentPageController> "
              + "Error registering payment (userId={}, session={}, subscription={})",
          userId,
          session.getId(),
          subscription.map(Subscription::getId).orElse("unknown"),
          ex);
      return DataBufferUtils.read(errorPage, new DefaultDataBufferFactory(), 4096);
    }
  }

  private Payment buildPayment(final Long userId, final Subscription subscription) {
    return new Payment(
        new PaymentId(UUID.randomUUID().toString()),
        new PaymentUser(new PaymentUserId(userId)),
        new PaymentSubscription(
            new PaymentSubscriptionStartDate(
                LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(subscription.getCurrentPeriodStart()), UTC)),
            new PaymentSubscriptionEndDate(
                LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(subscription.getCurrentPeriodEnd()), UTC))));
  }
}
