package com.adrianliz.savemypetrol.payment.infrastructure.api;

import com.adrianliz.savemypetrol.payment.application.FindActivePaymentService;
import com.adrianliz.savemypetrol.payment.application.UpdatePaymentUseCase;
import com.adrianliz.savemypetrol.payment.domain.PaymentSubscriptionCancelDate;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.adrianliz.savemypetrol.payment.infrastructure.stripe.StripeService;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@RestController
@AllArgsConstructor
public final class UnsubscribeController implements PaymentsControllerV1 {
  private final FindActivePaymentService findActivePaymentService;
  private final UpdatePaymentUseCase updatePaymentUseCase;
  private final StripeService stripeService;

  @DeleteMapping("/users/{userId}")
  public Mono<Void> unsubscribe(@PathVariable("userId") final Long userId) {
    final var paymentUserId = new PaymentUserId(userId);
    final var cancelDate = new PaymentSubscriptionCancelDate(LocalDateTime.now());

    return findActivePaymentService
        .execute(paymentUserId)
        .flatMap(
            payment -> {
              stripeService.unsubscribe(userId);
              return updatePaymentUseCase.execute(payment.cancel(cancelDate));
            });
  }
}
