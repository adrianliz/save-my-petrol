package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public abstract class UUIDIdentifier implements Serializable {
  public boolean isValid(final String value) {
    try {
      java.util.UUID.fromString(value);
      return true;
    } catch (final IllegalArgumentException e) {
      return false;
    }
  }
}
