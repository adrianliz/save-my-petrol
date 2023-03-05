package com.adrianliz.savemypetrol.subscription.domain;

import com.adrianliz.savemypetrol.common.domain.UUIDIdentifier;
import com.adrianliz.savemypetrol.match.domain.InvalidFindMatchProcessId;
import java.util.Objects;
import java.util.UUID;

public final class SubscriptionId extends UUIDIdentifier {

  public SubscriptionId(final String value) {
    super(value);
  }

  @Override
  protected UUID validate(final String value) {
    if (value == null || value.isBlank()) {
      throw new InvalidFindMatchProcessId();
    }
    try {
      return UUID.fromString(value);
    } catch (final IllegalArgumentException e) {
      throw new InvalidFindMatchProcessId();
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
    final SubscriptionId that = (SubscriptionId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
