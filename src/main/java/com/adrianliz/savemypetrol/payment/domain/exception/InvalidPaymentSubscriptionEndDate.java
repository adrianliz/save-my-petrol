package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPaymentSubscriptionEndDate extends SaveMyPetrolException {
  public InvalidPaymentSubscriptionEndDate() {
    super(HttpStatus.BAD_REQUEST, "Invalid payment subscription end date.");
  }
}
