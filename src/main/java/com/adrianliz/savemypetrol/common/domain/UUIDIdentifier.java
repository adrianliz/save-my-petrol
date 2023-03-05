package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;
import java.util.UUID;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public abstract class UUIDIdentifier implements Serializable {

  protected final UUID value;

  protected UUIDIdentifier(final String value) {
    this.value = validate(value);
  }

  protected abstract UUID validate(final String value);

  public UUID value() {
    return value;
  }

  public String valueAsString() {
    return value.toString();
  }
}
