package com.adrianliz.savemypetrol.subscriptions.domain;

public final class SubscriptionUser {
  private final SubscriptionUserId id;
  private final SubscriptionUserLocation location;
  private final SubscriptionUserDistance maxDistance;

  public SubscriptionUser(
      final SubscriptionUserId id,
      final SubscriptionUserLocation location,
      final SubscriptionUserDistance maxDistance) {
    this.id = id;
    this.location = location;
    this.maxDistance = maxDistance;
  }

  public SubscriptionUserId id() {
    return id;
  }

  public SubscriptionUserLocation location() {
    return location;
  }

  public SubscriptionUserDistance maxDistance() {
    return maxDistance;
  }
}
