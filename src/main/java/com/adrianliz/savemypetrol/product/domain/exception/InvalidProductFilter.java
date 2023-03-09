package com.adrianliz.savemypetrol.product.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidProductFilter extends SaveMyPetrolException {
  public InvalidProductFilter() {
    super(HttpStatus.BAD_REQUEST, "Invalid product filter.");
  }
}
