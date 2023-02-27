package com.adrianliz.savemypetrol.match.infrastructure.repository.record;

import lombok.Data;

@Data
public final class ProductMatchRecord {
  private final Integer typeId;
  private final Integer triggerCents;
}
