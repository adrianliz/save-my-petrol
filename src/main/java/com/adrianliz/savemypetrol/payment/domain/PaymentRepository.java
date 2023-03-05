package com.adrianliz.savemypetrol.payment.domain;

import reactor.core.publisher.Mono;

public interface PaymentRepository {

  Mono<Void> save(Payment payment);

  Mono<Payment> findActivePayment(final PaymentUserId paymentUserId);
}
