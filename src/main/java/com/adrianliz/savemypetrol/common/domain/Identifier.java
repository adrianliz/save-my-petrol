package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

public abstract class Identifier implements Serializable {
  protected boolean isValid(final Long value) {
    return value != null;
  }
}
