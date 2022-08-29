package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public abstract class StringValueObject implements Serializable {

  private final String value;
}
