package com.adrianliz.savemypetrol.trigger.domain;

import com.adrianliz.savemypetrol.trigger.domain.exception.InvalidTrigger;
import java.io.Serializable;
import java.util.Objects;

public final class Trigger implements Serializable {

  private final TriggerId id;
  private final TriggerTargetUser targetUser;
  private final TriggerTargetProduct targetProduct;

  public Trigger(
      final TriggerId id,
      final TriggerTargetUser targetUser,
      final TriggerTargetProduct targetProduct) {

    validate(id, targetUser, targetProduct);
    this.id = id;
    this.targetUser = targetUser;
    this.targetProduct = targetProduct;
  }

  private void validate(
      final TriggerId id,
      final TriggerTargetUser targetUser,
      final TriggerTargetProduct targetProduct) {

    if (id == null || targetUser == null || targetProduct == null) {
      throw new InvalidTrigger();
    }
  }

  public TriggerId id() {
    return id;
  }

  public TriggerTargetUser targetUser() {
    return targetUser;
  }

  public TriggerTargetProduct targetProduct() {
    return targetProduct;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Trigger that = (Trigger) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
