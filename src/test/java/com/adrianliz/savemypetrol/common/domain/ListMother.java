package com.adrianliz.savemypetrol.common.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public final class ListMother {

  public static <T> List<T> create(final Integer size, final Supplier<T> creator) {
    final var list = new ArrayList<T>();

    for (int i = 0; i < size; i++) {
      list.add(creator.get());
    }

    return list;
  }

  public static <T> List<T> random(final Supplier<T> creator) {
    return create(IntegerMother.random(), creator);
  }

  public static <T> List<T> one(final T element) {
    return Collections.singletonList(element);
  }
}
