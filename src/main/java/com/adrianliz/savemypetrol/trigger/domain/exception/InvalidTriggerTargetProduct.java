package com.adrianliz.savemypetrol.trigger.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidTriggerTargetProduct extends SaveMyPetrolException {
  public InvalidTriggerTargetProduct() {
    super(HttpStatus.BAD_REQUEST, "Invalid trigger target product.");
  }
}
