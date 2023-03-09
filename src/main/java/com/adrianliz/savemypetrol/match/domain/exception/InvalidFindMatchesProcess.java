package com.adrianliz.savemypetrol.match.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidFindMatchesProcess extends SaveMyPetrolException {
  public InvalidFindMatchesProcess() {
    super(HttpStatus.BAD_REQUEST, "Invalid find matches process.");
  }
}
