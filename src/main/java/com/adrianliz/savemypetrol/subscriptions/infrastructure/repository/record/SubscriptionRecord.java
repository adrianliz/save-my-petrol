package com.adrianliz.savemypetrol.subscriptions.infrastructure.repository.record;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class SubscriptionRecord {
  @Id @EqualsAndHashCode.Include private UUID id;

  private SubscriptionUserRecord user;

  private List<SubscriptionProductRecord> products;
}
