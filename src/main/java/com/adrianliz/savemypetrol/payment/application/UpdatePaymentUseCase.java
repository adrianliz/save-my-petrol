package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class UpdatePaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final FindPaymentUseCase findPaymentUseCase;

  public Mono<Void> execute(final Payment newPayment) {
    return findPaymentUseCase.execute(newPayment.user().id())
        .flatMap(unused -> paymentRepository.save(newPayment));
  }
}
