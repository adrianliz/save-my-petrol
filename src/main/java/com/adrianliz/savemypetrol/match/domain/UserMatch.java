package com.adrianliz.savemypetrol.match.domain;

import com.adrianliz.savemypetrol.match.domain.exception.InvalidUserMatch;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import java.io.Serializable;
import java.util.Objects;

public final class UserMatch implements Serializable {

  private final TriggerTargetUserId id;

  public UserMatch(final TriggerTargetUserId id) {
    validate(id);
    this.id = id;
  }

  private void validate(final TriggerTargetUserId id) {
    if (id == null) {
      throw new InvalidUserMatch();
    }
  }

  public TriggerTargetUserId id() {
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
