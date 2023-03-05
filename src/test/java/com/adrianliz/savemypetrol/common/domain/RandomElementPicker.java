package com.adrianliz.savemypetrol.common.domain;

import java.util.Random;

public final class RandomElementPicker {

  public static <T> T random(final T[] values) {
    return values[new Random().nextInt(values.length)];
  }
}
