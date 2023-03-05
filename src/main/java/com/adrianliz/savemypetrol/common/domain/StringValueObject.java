package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

public abstract class StringValueObject implements Serializable {

  protected final String value;

  protected StringValueObject(final String value) {
    validate(value);
    this.value = value;
  }

  protected abstract void validate(final String value);

  public String value() {
    return value;
  }
}
