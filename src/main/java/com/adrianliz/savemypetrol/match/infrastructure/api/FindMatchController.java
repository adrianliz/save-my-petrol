package com.adrianliz.savemypetrol.match.infrastructure.api;

import com.adrianliz.savemypetrol.match.application.FindMatchUseCase;
import com.adrianliz.savemypetrol.match.application.MatchResponse;
import com.adrianliz.savemypetrol.match.domain.FindMatchesProcess;
import com.adrianliz.savemypetrol.match.domain.FindMatchesProcessId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public final class FindMatchController implements MatchesControllerV1 {

  private final FindMatchUseCase findMatchUseCase;

  @GetMapping("/{id}")
  public Flux<MatchResponse> find(@PathVariable("id") final String id) {
    return findMatchUseCase
        .execute(new FindMatchesProcess(new FindMatchesProcessId(id)))
        .switchIfEmpty(Mono.error(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }
}
