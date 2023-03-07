package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTriggerTargetUser;
import java.io.Serializable;
import java.util.Objects;

public final class TriggerTargetUser implements Serializable {

  private final TriggerTargetUserId id;
  private final TriggerTargetUserLocation sourceLocation;
  private final TriggerTargetUserDistance maxDistanceFromSource;

  public TriggerTargetUser(
      final TriggerTargetUserId id,
      final TriggerTargetUserLocation sourceLocation,
      final TriggerTargetUserDistance maxDistanceFromSource) {

    validate(id, sourceLocation, maxDistanceFromSource);
    this.id = id;
    this.sourceLocation = sourceLocation;
    this.maxDistanceFromSource = maxDistanceFromSource;
  }

  private void validate(
      final TriggerTargetUserId id,
      final TriggerTargetUserLocation sourceLocation,
      final TriggerTargetUserDistance maxDistanceFromSource) {

    if (id == null || sourceLocation == null || maxDistanceFromSource == null) {
      throw new InvalidTriggerTargetUser();
    }
  }

  public TriggerTargetUserId id() {
    return id;
  }

  public TriggerTargetUserLocation sourceLocation() {
    return sourceLocation;
  }

  public TriggerTargetUserDistance maxDistanceFromSource() {
    return maxDistanceFromSource;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final TriggerTargetUser that = (TriggerTargetUser) o;
    return Objects.equals(id, that.id)
        && Objects.equals(sourceLocation, that.sourceLocation)
        && Objects.equals(maxDistanceFromSource, that.maxDistanceFromSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sourceLocation, maxDistanceFromSource);
  }
}
