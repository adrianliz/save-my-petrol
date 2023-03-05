package com.adrianliz.savemypetrol.subscription.application;

import com.adrianliz.savemypetrol.subscription.domain.Subscription;
import com.adrianliz.savemypetrol.subscription.domain.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class CreateSubscriptionUseCase {

  private final SubscriptionRepository subscriptionRepository;

  public Mono<Void> execute(final Subscription subscription) {
    return subscriptionRepository.save(subscription);
  }
}
