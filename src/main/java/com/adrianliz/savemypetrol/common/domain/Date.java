package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Date implements Serializable {
  protected final LocalDateTime value;

  protected Date(final LocalDateTime value) {
    validate(value);
    this.value = value;
  }

  protected abstract void validate(final LocalDateTime value);

  public LocalDateTime value() {
    return value;
  }
}
