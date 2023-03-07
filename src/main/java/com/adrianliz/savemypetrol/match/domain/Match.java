package com.adrianliz.savemypetrol.match.domain;

import com.adrianliz.savemypetrol.match.domain.exception.InvalidMatch;
import com.adrianliz.savemypetrol.trigger.domain.TriggerId;
import java.io.Serializable;
import java.util.Objects;

public final class Match implements Serializable {

  private final TriggerId id;
  private final UserMatch user;
  private final PetrolStationMatch petrolStation;

  public Match(final TriggerId id, final UserMatch user, final PetrolStationMatch petrolStation) {
    validate(id, user, petrolStation);
    this.id = id;
    this.user = user;
    this.petrolStation = petrolStation;
  }

  private void validate(
      final TriggerId id, final UserMatch user, final PetrolStationMatch petrolStation) {

    if (id == null || user == null || petrolStation == null) {
      throw new InvalidMatch();
    }
  }

  public TriggerId id() {
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
