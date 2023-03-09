package com.adrianliz.savemypetrol.payment.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class PaymentPageGenerationError extends SaveMyPetrolException {
  public PaymentPageGenerationError() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "Payment page generation error.");
  }
}
