package com.adrianliz.savemypetrol.common.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Page {
  private final Integer maxElements;

  private final Integer offset;

  public static Page of(final Integer maxElements, final Integer offset) {
    final int validNumber =
        maxElements != null && maxElements >= 0 ? maxElements : Integer.MAX_VALUE;
    final int validOffset = offset != null && offset >= 0 ? offset : 0;

    return new Page(validNumber, validOffset);
  }

  public static Page defaultPage() {
    return of(null, null);
  }

  public boolean hasMaxElements() {
    return maxElements != 0;
  }

  public Integer calculateSkippedElements() {
    return offset;
  }

  public Integer calculateMaxElements() {
    return maxElements;
  }
}
