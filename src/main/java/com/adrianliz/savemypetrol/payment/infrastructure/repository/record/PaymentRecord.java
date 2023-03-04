package com.adrianliz.savemypetrol.payment.infrastructure.repository.record;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class PaymentRecord {
  @Id
  @EqualsAndHashCode.Include
  private UUID id;

  private Long userId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private LocalDateTime cancelDate;
}
