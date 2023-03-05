package com.adrianliz.savemypetrol.common.domain;

public final class LongMother {

  public static Long random() {
    return MotherCreator.random().number().randomNumber();
  }
}
