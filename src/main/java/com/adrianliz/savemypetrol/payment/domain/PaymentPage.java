package com.adrianliz.savemypetrol.payment.domain;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public final class PaymentPage implements Serializable {

  private final String url;

  public PaymentPage(final String url) {
    this.url = validate(url).toExternalForm();
  }

  private URL validate(final String url) {
    if (url == null || url.isEmpty()) {
      throw new InvalidPaymentPage();
    }
    try {
      return new URL(url);
    } catch (final MalformedURLException e) {
      throw new InvalidPaymentPage();
    }
  }

  public String url() {
    return url;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PaymentPage that = (PaymentPage) o;
    return Objects.equals(url, that.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url);
  }
}
