package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.common.domain.UUIDIdentifier;
import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTriggerId;
import java.util.Objects;
import java.util.UUID;

public final class TriggerId extends UUIDIdentifier {

  public TriggerId(final String value) {
    super(value);
  }

  @Override
  protected UUID validate(final String value) {
    if (value == null || value.isBlank()) {
      throw new InvalidTriggerId();
    }
    try {
      return UUID.fromString(value);
    } catch (final IllegalArgumentException e) {
      throw new InvalidTriggerId();
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
    final TriggerId that = (TriggerId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
