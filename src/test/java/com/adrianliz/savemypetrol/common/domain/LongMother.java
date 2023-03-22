package com.adrianliz.savemypetrol.common.domain;

public final class LongMother {

  public static Long random() {
    return MotherCreator.random().number().randomNumber();
  }

  public static Long randomBetween(final Long min, final Long max) {
    return MotherCreator.random().number().numberBetween(min, max);
  }
}
