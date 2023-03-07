package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.Location;
import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTriggerTargetUserLocation;
import java.util.Objects;

public final class TriggerTargetUserLocation extends Location {

  public TriggerTargetUserLocation(final Double latitude, final Double longitude) {
    super(latitude, longitude);
  }

  @Override
  protected void validate(final Double latitude, final Double longitude) {
    if (!coordinatesAreValid(latitude, longitude)) {
      throw new InvalidTriggerTargetUserLocation();
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
    final TriggerTargetUserLocation location = (TriggerTargetUserLocation) o;
    return Objects.equals(latitude, location.latitude)
        && Objects.equals(longitude, location.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }
}
