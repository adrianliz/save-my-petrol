package com.adrianliz.savemypetrol.payment.domain;

import java.util.Optional;

public interface PaymentPageGenerator {

  Optional<PaymentPage> generate(final PaymentUserId paymentUserId);
}
