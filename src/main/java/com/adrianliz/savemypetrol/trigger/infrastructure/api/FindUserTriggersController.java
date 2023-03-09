package com.adrianliz.savemypetrol.trigger.infrastructure.api;

import com.adrianliz.savemypetrol.trigger.application.TriggerResponse;
import com.adrianliz.savemypetrol.trigger.application.UserTriggersFinder;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public final class FindUserTriggersController implements TriggersControllerV1 {
  private final UserTriggersFinder userTriggersFinder;

  @GetMapping("/users/{userId}")
  public Flux<TriggerResponse> find(@PathVariable("userId") @NotNull final Long userId) {
    return userTriggersFinder.find(new TriggerTargetUserId(userId)).map(TriggerResponse::from);
  }
}
