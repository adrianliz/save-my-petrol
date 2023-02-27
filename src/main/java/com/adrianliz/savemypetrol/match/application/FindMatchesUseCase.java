package com.adrianliz.savemypetrol.match.application;

import com.adrianliz.savemypetrol.match.domain.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public final class FindMatchesUseCase {
  private final MatchRepository matchRepository;

  public Mono<FindMatchesResponse> execute() {
    return matchRepository.findAll().map(FindMatchesResponse::from);
  }
}
