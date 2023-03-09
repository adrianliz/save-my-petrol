package com.adrianliz.savemypetrol.station.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public class InvalidPetrolStationFilter extends SaveMyPetrolException {
  public InvalidPetrolStationFilter() {
    super(HttpStatus.BAD_REQUEST, "Invalid petrol station filter.");
  }
}
