package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@Service
@AllArgsConstructor
public final class UpdatePaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final FindActivePaymentService findActivePaymentService;

  public Mono<Void> execute(final Payment newPayment) {
    return findActivePaymentService.execute(newPayment.user().id())
        .flatMap(unused -> paymentRepository.save(newPayment));
  }
}
