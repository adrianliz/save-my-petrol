package com.adrianliz.savemypetrol.station.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidPetrolStationProductType extends SaveMyPetrolException {
  public InvalidPetrolStationProductType() {
    super(HttpStatus.BAD_REQUEST, "Invalid petrol station product type.");
  }
}
