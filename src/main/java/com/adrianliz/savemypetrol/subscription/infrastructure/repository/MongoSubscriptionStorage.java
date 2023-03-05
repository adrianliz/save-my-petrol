package com.adrianliz.savemypetrol.subscription.infrastructure.repository;

import com.adrianliz.savemypetrol.subscription.domain.Subscription;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoSubscriptionStorage implements SubscriptionRepository {

  private final ReactiveMongoTemplate dataAccessor;

  @Override
  public Mono<Void> save(final Subscription subscription) {
    return dataAccessor.save(SubscriptionConverter.toRecord(subscription)).then();
  }
}
