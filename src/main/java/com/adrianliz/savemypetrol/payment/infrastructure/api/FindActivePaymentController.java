package com.adrianliz.savemypetrol.payment.infrastructure.api;

import com.adrianliz.savemypetrol.payment.application.FindActivePaymentUseCase;
import com.adrianliz.savemypetrol.payment.application.PaymentResponse;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@RestController
@AllArgsConstructor
public final class FindActivePaymentController implements PaymentsControllerV1 {

  private final FindActivePaymentUseCase findActivePaymentUseCase;

  @GetMapping("/users/{userId}")
  public Mono<PaymentResponse> find(@PathVariable("userId") final Long userId) {
    return findActivePaymentUseCase.find(new PaymentUserId(userId));
  }
}
