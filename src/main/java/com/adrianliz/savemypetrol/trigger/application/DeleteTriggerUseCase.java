package com.adrianliz.savemypetrol.trigger.application;

import com.adrianliz.savemypetrol.trigger.domain.TriggerId;
import com.adrianliz.savemypetrol.trigger.domain.TriggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class DeleteTriggerUseCase {
  private final TriggerRepository triggerRepository;

  public Mono<Void> delete(final TriggerId id) {
    return triggerRepository.delete(id);
  }
}
