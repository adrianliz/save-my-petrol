package com.adrianliz.savemypetrol.trigger.infrastructure.repository;

import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerId;
import com.adrianliz.savemypetrol.trigger.domain.TriggerRepository;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerRecord;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoTriggerStorage implements TriggerRepository {

  private final ReactiveMongoTemplate dataAccessor;

  @Override
  public Mono<Void> save(final Trigger trigger) {
    return dataAccessor.save(TriggerConverter.toRecord(trigger)).then();
  }

  @Override
  public Flux<Trigger> findByUserId(final TriggerTargetUserId id) {
    return dataAccessor
        .find(Query.query(Criteria.where("userId").is(id.value())), TriggerRecord.class)
        .map(TriggerConverter::toEntity);
  }

  @Override
  public Mono<Void> delete(final TriggerId id) {
    return dataAccessor
        .findAndRemove(Query.query(Criteria.where("id").is(id.value())), TriggerRecord.class)
        .then();
  }
}
