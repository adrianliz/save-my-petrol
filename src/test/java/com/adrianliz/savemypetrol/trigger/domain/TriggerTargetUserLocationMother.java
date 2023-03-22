package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.AddressMother;
import com.github.javafaker.Address;
import java.text.NumberFormat;
import java.util.Locale;
import lombok.SneakyThrows;

public final class TriggerTargetUserLocationMother {
  @SneakyThrows
  public static TriggerTargetUserLocation create(final Address value) {
    final var format = NumberFormat.getInstance(Locale.FRANCE);

    return new TriggerTargetUserLocation(
        format.parse(value.latitude()).doubleValue(),
        format.parse(value.longitude()).doubleValue());
  }

  public static TriggerTargetUserLocation random() {
    return create(AddressMother.random());
  }
}
