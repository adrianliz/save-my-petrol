package com.adrianliz.savemypetrol.trigger.infrastructure.api;

import com.adrianliz.savemypetrol.trigger.application.CreateTriggerUseCase;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public final class CreateTriggerController implements TriggersControllerV1 {

  private final CreateTriggerUseCase createTriggerUseCase;

  @PostMapping
  public Mono<Void> create(
      @RequestBody @NotNull @Valid final CreateTriggerRequest createTriggerRequest) {
    return createTriggerUseCase.execute(createTriggerRequest.buildTrigger());
  }
}
