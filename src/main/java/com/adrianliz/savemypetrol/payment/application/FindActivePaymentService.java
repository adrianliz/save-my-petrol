package com.adrianliz.savemypetrol.payment.application;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentRepository;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@Service
@AllArgsConstructor
public final class FindActivePaymentService {

  private final PaymentRepository paymentRepository;

  public Mono<Payment> execute(final PaymentUserId paymentUserId) {
    return paymentRepository.findActivePayment(paymentUserId);
  }
}
