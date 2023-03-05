package com.adrianliz.savemypetrol.match.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class FindMatchesProcess implements Serializable {

  private final FindMatchesProcessId id;

  public FindMatchesProcess(final FindMatchesProcessId id) {
    this.id = id;
  }

  public static FindMatchesProcess create() {
    return new FindMatchesProcess(new FindMatchesProcessId(UUID.randomUUID().toString()));
  }

  public FindMatchesProcessId id() {
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
    final FindMatchesProcess that = (FindMatchesProcess) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
