package com.adrianliz.savemypetrol.payment.infrastructure.repository;

import com.adrianliz.savemypetrol.payment.domain.Payment;
import com.adrianliz.savemypetrol.payment.domain.PaymentRepository;
import com.adrianliz.savemypetrol.payment.domain.PaymentUserId;
import com.adrianliz.savemypetrol.payment.infrastructure.repository.record.PaymentRecord;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoPaymentStorage implements PaymentRepository {
  private final ReactiveMongoTemplate dataAccessor;

  @Override
  public Mono<Void> save(final Payment payment) {
    return dataAccessor.save(PaymentConverter.toRecord(payment)).then();
  }

  @Override
  public Mono<Payment> findActivePayment(final PaymentUserId paymentUserId) {
    final LocalDateTime now = LocalDateTime.now();
    return dataAccessor.find(
            Query.query(
                Criteria.where("userId").is(paymentUserId.value())
                    .and("cancelDate").is(null)
                    .and("endDate").gt(now)
                    .and("startDate").lt(now)),
            PaymentRecord.class)
        .next()
        .map(PaymentConverter::toDomain);
  }
}
