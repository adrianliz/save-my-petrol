package com.adrianliz.savemypetrol.station.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPetrolStationId extends SaveMyPetrolException {
  public InvalidPetrolStationId() {
    super(HttpStatus.BAD_REQUEST, "Invalid petrol station id.");
  }
}
