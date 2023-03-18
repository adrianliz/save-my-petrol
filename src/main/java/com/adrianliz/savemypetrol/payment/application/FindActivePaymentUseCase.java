package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.adrianliz.savemypetrol.payment.domain.exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class FindActivePaymentUseCase {
  private final FindActivePaymentService findActivePaymentService;

  public Mono<PaymentResponse> find(final PaymentUserId paymentUserId) {
    return findActivePaymentService
        .execute(paymentUserId)
        .map(PaymentResponse::from)
        .switchIfEmpty(Mono.defer(() -> Mono.error(new PaymentNotFoundException())));
  }
}
