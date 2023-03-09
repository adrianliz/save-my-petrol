package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class PaymentNotFoundException extends SaveMyPetrolException {
  public PaymentNotFoundException() {
    super(HttpStatus.NOT_FOUND, "Payment not found.");
  }
}
