package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPaymentPage extends SaveMyPetrolException {
  public InvalidPaymentPage() {
    super(HttpStatus.BAD_REQUEST, "Invalid payment page.");
  }
}
