package com.adrianliz.savemypetrol.match.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidMatch extends SaveMyPetrolException {
  public InvalidMatch() {
    super(HttpStatus.BAD_REQUEST, "Invalid match.");
  }
}
