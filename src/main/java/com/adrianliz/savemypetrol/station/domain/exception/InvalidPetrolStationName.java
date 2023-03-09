package com.adrianliz.savemypetrol.station.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPetrolStationName extends SaveMyPetrolException {
  public InvalidPetrolStationName() {
    super(HttpStatus.BAD_REQUEST, "Invalid petrol station name.");
  }
}
