package com.adrianliz.savemypetrol.subscriptions.infrastructure.api;

import com.adrianliz.savemypetrol.subscriptions.application.CreateSubscriptionUseCase;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public final class CreateSubscriptionController implements SubscriptionsControllerV1 {
  private final CreateSubscriptionUseCase createSubscriptionUseCase;

  @PostMapping
  public Mono<Void> create(
      @RequestBody @NotNull @Valid final CreateSubscriptionRequest createSubscriptionRequest) {
    return createSubscriptionUseCase.execute(createSubscriptionRequest.buildSubscription());
  }
}
