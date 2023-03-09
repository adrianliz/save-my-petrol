package com.adrianliz.savemypetrol.match.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidUserMatch extends SaveMyPetrolException {
  public InvalidUserMatch() {
    super(HttpStatus.BAD_REQUEST, "Invalid user match.");
  }
}
