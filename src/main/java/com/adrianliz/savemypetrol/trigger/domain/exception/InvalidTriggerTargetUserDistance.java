package com.adrianliz.savemypetrol.trigger.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidTriggerTargetUserDistance extends SaveMyPetrolException {
  public InvalidTriggerTargetUserDistance() {
    super(HttpStatus.BAD_REQUEST, "Invalid trigger target user distance.");
  }
}
