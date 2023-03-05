package com.adrianliz.savemypetrol.common.domain;

public class DoubleMother {

  public static Double randomBetween(final Long min, final Long max) {
    return MotherCreator.random().number().randomDouble(2, min, max);
  }
}
