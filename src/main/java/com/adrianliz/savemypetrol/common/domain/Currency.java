package com.adrianliz.savemypetrol.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
  EUR("Euros", "€");

  private final String name;
  private final String symbol;
}
