package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPaymentUserId extends SaveMyPetrolException {
  public InvalidPaymentUserId() {
    super(HttpStatus.BAD_REQUEST, "Invalid payment user id.");
  }
}
