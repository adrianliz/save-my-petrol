package com.adrianliz.savemypetrol.trigger.domain;

import reactor.core.publisher.Mono;

public interface TriggerRepository {

  Mono<Void> save(final Trigger trigger);
}
