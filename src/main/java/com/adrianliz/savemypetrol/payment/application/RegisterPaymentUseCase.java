package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RegisterPaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final FindActivePaymentService findActivePaymentService;

  public Mono<Void> execute(final Payment payment) {
    return findActivePaymentService.execute(payment.user().id())
        .switchIfEmpty(Mono.defer(() -> paymentRepository.save(payment).map(unused -> payment)))
        .then();
  }
}
