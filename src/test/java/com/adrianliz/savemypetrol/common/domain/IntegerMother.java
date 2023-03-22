package com.adrianliz.savemypetrol.common.domain;

public final class IntegerMother {

  public static Integer random() {
    return MotherCreator.random().number().randomDigitNotZero();
  }

  public static Integer random(final Integer min, final Integer max) {
    return MotherCreator.random().number().numberBetween(min, max);
  }
}
