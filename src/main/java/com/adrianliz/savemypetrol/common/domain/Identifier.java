package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public abstract class Identifier implements Serializable {

  protected final Long value;
}
