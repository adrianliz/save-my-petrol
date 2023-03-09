package com.adrianliz.savemypetrol.trigger.infrastructure.api;

import com.adrianliz.savemypetrol.trigger.application.DeleteTriggerUseCase;
import com.adrianliz.savemypetrol.trigger.domain.TriggerId;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public final class DeleteTriggerController implements TriggersControllerV1 {
  private final DeleteTriggerUseCase deleteTriggerUseCase;

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") @NotNull final String id) {
    return deleteTriggerUseCase.delete(new TriggerId(id));
  }
}
