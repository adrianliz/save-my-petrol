package com.adrianliz.savemypetrol.trigger.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class TooManyTriggersCreated extends SaveMyPetrolException {
  public TooManyTriggersCreated() {
    super(HttpStatus.BAD_REQUEST, "Too many triggers created.");
  }
}
