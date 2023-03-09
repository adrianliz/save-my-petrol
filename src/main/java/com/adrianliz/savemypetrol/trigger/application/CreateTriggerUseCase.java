package com.adrianliz.savemypetrol.trigger.application;

import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerRepository;
import com.adrianliz.savemypetrol.trigger.domain.exception.TooManyTriggersCreated;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class CreateTriggerUseCase {

  private final UserTriggersFinder userTriggersFinder;
  private final TriggerRepository triggerRepository;

  public Mono<Void> execute(final Trigger trigger) {
    return userTriggersFinder
        .find(trigger.targetUser().id())
        .count()
        .flatMap(
            userTriggers ->
                Mono.defer(
                    () -> {
                      if (userTriggers >= 3) {
                        return Mono.error(new TooManyTriggersCreated());
                      }
                      return triggerRepository.save(trigger);
                    }));
  }
}
