package com.adrianliz.savemypetrol.match.domain;

import com.adrianliz.savemypetrol.subscription.domain.SubscriptionId;
import java.io.Serializable;
import java.util.Objects;

public final class Match implements Serializable {

  private final SubscriptionId id;
  private final UserMatch user;
  private final PetrolStationMatch petrolStation;

  public Match(
      final SubscriptionId id, final UserMatch user, final PetrolStationMatch petrolStation) {
    this.id = id;
    this.user = user;
    this.petrolStation = petrolStation;
  }

  public SubscriptionId id() {
    return id;
  }

  public UserMatch user() {
    return user;
  }

  public PetrolStationMatch petrolStation() {
    return petrolStation;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Match that = (Match) o;
    return Objects.equals(id, that.id)
        && Objects.equals(user, that.user)
        && Objects.equals(petrolStation, that.petrolStation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, petrolStation);
  }
}
