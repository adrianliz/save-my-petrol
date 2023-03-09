package com.adrianliz.savemypetrol.trigger.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidTriggerId extends SaveMyPetrolException {
  public InvalidTriggerId() {
    super(HttpStatus.BAD_REQUEST, "Invalid trigger id.");
  }
}
