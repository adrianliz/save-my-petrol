package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

public abstract class IncrementalIdentifier implements Serializable {
  protected boolean isValid(final Long value) {
    return value != null && value > 0;
  }
}
