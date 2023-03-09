package com.adrianliz.savemypetrol.common.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SaveMyPetrolException extends RuntimeException {
  private final HttpStatus responseStatus;
  private final String message;

  public SaveMyPetrolException(final HttpStatus responseStatus, final String message) {
    this.responseStatus =
        responseStatus != null ? responseStatus : HttpStatus.INTERNAL_SERVER_ERROR;
    this.message = message != null ? message : "Unknown error";
  }

  public SaveMyPetrolException() {
    this(null, null);
  }

  public ResponseStatusException toResponseStatusException() {
    return new ResponseStatusException(responseStatus, message);
  }
}
