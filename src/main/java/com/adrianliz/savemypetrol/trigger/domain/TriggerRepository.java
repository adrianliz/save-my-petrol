package com.adrianliz.savemypetrol.trigger.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TriggerRepository {

  Mono<Void> save(final Trigger trigger);

  Flux<Trigger> findByUserId(final TriggerTargetUserId id);
}
