package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

public abstract class StringValueObject implements Serializable {
  protected boolean isValid(final String value) {
    return value != null && !value.isBlank();
  }
}
