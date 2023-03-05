package com.adrianliz.savemypetrol.common.domain;

import java.io.Serializable;
import java.util.Objects;

public class Page implements Serializable {

  private final Integer maxElements;

  private final Integer offset;

  private Page(final Integer maxElements, final Integer offset) {
    this.maxElements = maxElements;
    this.offset = offset;
  }

  public static Page of(final Integer maxElements, final Integer offset) {
    final int validNumber =
        maxElements != null && maxElements >= 0 ? maxElements : Integer.MAX_VALUE;
    final int validOffset = offset != null && offset >= 0 ? offset : 0;

    return new Page(validNumber, validOffset);
  }

  public static Page defaultPage() {
    return of(null, null);
  }

  public Integer calculateSkippedElements() {
    return offset;
  }

  public Integer calculateMaxElements() {
    return maxElements;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Page page = (Page) o;
    return Objects.equals(maxElements, page.maxElements) && Objects.equals(offset, page.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxElements, offset);
  }

  @Override
  public String toString() {
    return "Page [maxElements=" + maxElements + ", offset=" + offset + "]";
  }
}
