package com.adrianliz.savemypetrol.match.domain;

import com.adrianliz.savemypetrol.subscription.domain.SubscriptionUserId;
import java.io.Serializable;
import java.util.Objects;

public final class UserMatch implements Serializable {

  private final SubscriptionUserId id;

  public UserMatch(final SubscriptionUserId id) {
    this.id = id;
  }

  public SubscriptionUserId id() {
    return id;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final UserMatch userMatch = (UserMatch) o;
    return Objects.equals(id, userMatch.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
