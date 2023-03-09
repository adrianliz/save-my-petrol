package com.adrianliz.savemypetrol.trigger.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidTriggerTargetProductPrice extends SaveMyPetrolException {
  public InvalidTriggerTargetProductPrice() {
    super(HttpStatus.BAD_REQUEST, "Invalid trigger target product price.");
  }
}
