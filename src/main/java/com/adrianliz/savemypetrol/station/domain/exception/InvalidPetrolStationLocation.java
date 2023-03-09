package com.adrianliz.savemypetrol.station.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPetrolStationLocation extends SaveMyPetrolException {
  public InvalidPetrolStationLocation() {
    super(HttpStatus.BAD_REQUEST, "Invalid petrol station location.");
  }
}
