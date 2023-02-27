package com.adrianliz.savemypetrol.subscription.domain;

import com.adrianliz.savemypetrol.common.domain.Location;

public final class SubscriptionUserLocation extends Location {
  public SubscriptionUserLocation(final Double latitude, final Double longitude) {
    super(latitude, longitude);
  }
}
