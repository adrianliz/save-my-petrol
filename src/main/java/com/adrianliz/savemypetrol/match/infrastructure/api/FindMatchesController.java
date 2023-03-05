package com.adrianliz.savemypetrol.match.infrastructure.api;

import com.adrianliz.savemypetrol.match.application.FindMatchesResponse;
import com.adrianliz.savemypetrol.match.application.FindMatchesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public final class FindMatchesController implements MatchesControllerV1 {

  private final FindMatchesUseCase findMatchesUseCase;

  @GetMapping
  public Mono<FindMatchesResponse> find() {
    return findMatchesUseCase.execute();
  }
}
