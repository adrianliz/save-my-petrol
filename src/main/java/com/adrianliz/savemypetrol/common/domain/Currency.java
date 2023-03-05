package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;

public enum Currency implements Serializable {
  EUR("Euros", "€");

  private final String name;
  private final String symbol;

  Currency(final String name, final String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public String symbolName() {
    return name;
  }
}
