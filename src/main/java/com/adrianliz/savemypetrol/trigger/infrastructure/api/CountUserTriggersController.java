package com.adrianliz.savemypetrol.trigger.infrastructure.api;

import com.adrianliz.savemypetrol.trigger.application.UserTriggersFinder;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public final class CountUserTriggersController implements TriggersControllerV1 {
  private final UserTriggersFinder userTriggersFinder;

  @GetMapping("/users/{userId}/count")
  private Mono<Integer> count(@PathVariable("userId") @NotNull final Long userId) {
    return userTriggersFinder.find(new TriggerTargetUserId(userId)).count().map(Long::intValue);
  }
}
