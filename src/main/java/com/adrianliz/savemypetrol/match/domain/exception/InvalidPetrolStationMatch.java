package com.adrianliz.savemypetrol.match.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPetrolStationMatch extends SaveMyPetrolException {
  public InvalidPetrolStationMatch() {
    super(HttpStatus.BAD_REQUEST, "Invalid petrol station match.");
  }
}
