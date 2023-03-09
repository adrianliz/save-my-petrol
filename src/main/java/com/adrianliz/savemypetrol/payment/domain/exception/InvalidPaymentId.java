package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPaymentId extends SaveMyPetrolException {
  public InvalidPaymentId() {
    super(HttpStatus.BAD_REQUEST, "Invalid payment id.");
  }
}
