package com.adrianliz.savemypetrol.stations.domain;

import com.adrianliz.savemypetrol.common.domain.AddressMother;
import com.adrianliz.savemypetrol.common.domain.Location;
import com.github.javafaker.Address;
import java.text.NumberFormat;
import java.util.Locale;
import lombok.SneakyThrows;

public final class PetrolStationLocationMother {
  @SneakyThrows
  public static PetrolStationLocation create(final Address value) {
    final var format = NumberFormat.getInstance(Locale.FRANCE);

    return new PetrolStationLocation(
        format.parse(value.latitude()).doubleValue(),
        format.parse(value.longitude()).doubleValue(),
        value.fullAddress());
  }

  public static PetrolStationLocation random() {
    return create(AddressMother.random());
  }

  public static PetrolStationLocation randomWithLocation(final Location location) {

    return new PetrolStationLocation(location, AddressMother.random().fullAddress());
  }

  public static PetrolStationLocation move(
      final PetrolStationLocation sourceLocation, final Double distanceInMeters) {

    return randomWithLocation(sourceLocation.move(distanceInMeters));
  }
}
