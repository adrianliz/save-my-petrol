package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPaymentUser extends SaveMyPetrolException {
  public InvalidPaymentUser() {
    super(HttpStatus.BAD_REQUEST, "Invalid payment user.");
  }
}
