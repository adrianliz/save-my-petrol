package com.adrianliz.savemypetrol.subscription.domain;

import java.io.Serializable;
import java.util.Objects;

public final class SubscriptionUser implements Serializable {
  private final SubscriptionUserId id;
  private final SubscriptionUserLocation sourceLocation;
  private final SubscriptionUserDistance maxDistanceFromSource;

  public SubscriptionUser(
      final SubscriptionUserId id,
      final SubscriptionUserLocation sourceLocation,
      final SubscriptionUserDistance maxDistanceFromSource) {
    this.id = id;
    this.sourceLocation = sourceLocation;
    this.maxDistanceFromSource = maxDistanceFromSource;
  }

  public SubscriptionUserId id() {
    return id;
  }

  public SubscriptionUserLocation sourceLocation() {
    return sourceLocation;
  }

  public SubscriptionUserDistance maxDistanceFromSource() {
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
    final SubscriptionUser that = (SubscriptionUser) o;
    return Objects.equals(id, that.id)
        && Objects.equals(sourceLocation, that.sourceLocation)
        && Objects.equals(maxDistanceFromSource, that.maxDistanceFromSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sourceLocation, maxDistanceFromSource);
  }
}
