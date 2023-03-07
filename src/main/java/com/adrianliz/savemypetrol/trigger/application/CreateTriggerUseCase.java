package com.adrianliz.savemypetrol.trigger.application;

import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class CreateTriggerUseCase {

  private final TriggerRepository triggerRepository;

  public Mono<Void> execute(final Trigger trigger) {
    return triggerRepository.save(trigger);
  }
}
