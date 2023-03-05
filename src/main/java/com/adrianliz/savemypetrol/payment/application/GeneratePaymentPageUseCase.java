package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.PaymentPageGenerationError;
import com.adrianliz.savemypetrol.payment.domain.PaymentPageGenerator;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class GeneratePaymentPageUseCase {

  private final PaymentPageGenerator paymentPageGenerator;

  public Mono<PaymentPageResponse> execute(final PaymentUserId paymentUserId) {
    return Mono.defer(
        () -> Mono.just(
            PaymentPageResponse.from(paymentPageGenerator.generate(paymentUserId)
                .orElseThrow(PaymentPageGenerationError::new))));
  }
}
