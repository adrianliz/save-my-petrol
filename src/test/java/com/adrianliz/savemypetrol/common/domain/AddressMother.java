package com.adrianliz.savemypetrol.common.domain;

import com.github.javafaker.Address;

public final class AddressMother {

  public static Address random() {
    return MotherCreator.random().address();
  }
}
