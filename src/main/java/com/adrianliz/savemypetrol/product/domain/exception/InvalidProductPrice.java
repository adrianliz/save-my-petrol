package com.adrianliz.savemypetrol.product.domain.exception;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import org.springframework.http.HttpStatus;

public final class InvalidProductPrice extends SaveMyPetrolException {
  public InvalidProductPrice() {
    super(HttpStatus.BAD_REQUEST, "Invalid product price.");
  }
}
