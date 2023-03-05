package com.adrianliz.savemypetrol.subscription.domain;

import reactor.core.publisher.Mono;

public interface SubscriptionRepository {

  Mono<Void> save(final Subscription subscription);
}
