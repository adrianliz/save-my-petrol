package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

public abstract class IncrementalIdentifier implements Serializable {

  protected final Long value;

  protected IncrementalIdentifier(final Long value) {
    validate(value);
    this.value = value;
  }

  protected abstract void validate(final Long value);

  public Long value() {
    return value;
  }
}
