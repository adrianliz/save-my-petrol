package com.adrianliz.savemypetrol.match.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidFindMatchesProcessId extends SaveMyPetrolException {
  public InvalidFindMatchesProcessId() {
    super(HttpStatus.BAD_REQUEST, "Invalid find matches process id.");
  }
}
