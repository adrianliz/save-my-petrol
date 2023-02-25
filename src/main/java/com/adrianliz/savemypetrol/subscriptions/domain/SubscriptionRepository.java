package com.adrianliz.savemypetrol.subscriptions.domain;

import reactor.core.publisher.Mono;

public interface SubscriptionRepository {
  Mono<Void> save(Subscription subscription);
}
