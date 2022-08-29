package com.adrianliz.savemypetrol.products.domain;

import com.adrianliz.savemypetrol.common.domain.Currency;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductPrice implements Serializable {
  private final Long cents;
  private final Currency currency;
}
