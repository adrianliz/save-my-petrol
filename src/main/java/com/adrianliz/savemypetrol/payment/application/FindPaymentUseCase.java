package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentRepository;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class FindPaymentUseCase {

  private final PaymentRepository paymentRepository;

  public Mono<Payment> execute(final PaymentUserId paymentUserId) {
    return paymentRepository.findActivePayment(paymentUserId);
  }
}
