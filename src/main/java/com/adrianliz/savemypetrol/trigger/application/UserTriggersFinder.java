package com.adrianliz.savemypetrol.trigger.application;

import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerRepository;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public final class UserTriggersFinder {
  private final TriggerRepository triggerRepository;

  public Flux<Trigger> find(final TriggerTargetUserId id) {
    return triggerRepository.findByUserId(id);
  }
}
