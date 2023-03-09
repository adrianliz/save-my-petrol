package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPaymentSubscriptionStartDate extends SaveMyPetrolException {
  public InvalidPaymentSubscriptionStartDate() {
    super(HttpStatus.BAD_REQUEST, "Invalid payment subscription start date.");
  }
}
