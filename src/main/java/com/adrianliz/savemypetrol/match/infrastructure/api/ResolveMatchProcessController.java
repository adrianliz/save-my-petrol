package com.adrianliz.savemypetrol.match.infrastructure.api;

import com.adrianliz.savemypetrol.match.application.MatchResponse;
import com.adrianliz.savemypetrol.match.application.ResolveMatchProcessUseCase;
import com.adrianliz.savemypetrol.match.domain.FindMatchesProcess;
import com.adrianliz.savemypetrol.match.domain.FindMatchesProcessId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public final class ResolveMatchProcessController implements MatchesControllerV1 {

  private final ResolveMatchProcessUseCase resolveMatchProcessUseCase;

  @GetMapping("/processes/{id}")
  public Flux<MatchResponse> find(@PathVariable("id") final String id) {
    return resolveMatchProcessUseCase.execute(new FindMatchesProcess(new FindMatchesProcessId(id)));
  }
}
