package com.adrianliz.savemypetrol.payment.domain;

import com.adrianliz.savemypetrol.payment.domain.exception.InvalidPaymentUser;
import java.io.Serializable;
import java.util.Objects;

public final class PaymentUser implements Serializable {

  private final PaymentUserId id;

  public PaymentUser(final PaymentUserId id) {
    validate(id);
    this.id = id;
  }

  private void validate(final PaymentUserId id) {
    if (id == null) {
      throw new InvalidPaymentUser();
    }
  }

  public PaymentUserId id() {
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
    final PaymentUser that = (PaymentUser) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
