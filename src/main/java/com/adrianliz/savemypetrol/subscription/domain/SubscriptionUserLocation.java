package com.adrianliz.savemypetrol.subscription.domain;

import com.adrianliz.savemypetrol.common.domain.Location;
import java.util.Objects;

public final class SubscriptionUserLocation extends Location {

  public SubscriptionUserLocation(final Double latitude, final Double longitude) {
    super(latitude, longitude);
  }

  @Override
  protected void validate(final Double latitude, final Double longitude) {
    if (!coordinatesAreValid(latitude, longitude)) {
      throw new InvalidSubscriptionUserLocation();
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SubscriptionUserLocation location = (SubscriptionUserLocation) o;
    return Objects.equals(latitude, location.latitude) && Objects.equals(longitude,
        location.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }
}
