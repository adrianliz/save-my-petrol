package com.adrianliz.savemypetrol.match.application;

import com.adrianliz.savemypetrol.match.domain.FindMatchesProcess;
import com.adrianliz.savemypetrol.match.domain.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public final class ResolveMatchProcessUseCase {

  private final MatchRepository matchRepository;

  public Flux<MatchResponse> execute(final FindMatchesProcess process) {
    return matchRepository.resolve(process).map(MatchResponse::from);
  }
}
