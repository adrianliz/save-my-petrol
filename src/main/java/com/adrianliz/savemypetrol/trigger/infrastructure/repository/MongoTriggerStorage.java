package com.adrianliz.savemypetrol.trigger.infrastructure.repository;

import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoTriggerStorage implements TriggerRepository {

  private final ReactiveMongoTemplate dataAccessor;

  @Override
  public Mono<Void> save(final Trigger trigger) {
    return dataAccessor.save(TriggerConverter.toRecord(trigger)).then();
  }
}
