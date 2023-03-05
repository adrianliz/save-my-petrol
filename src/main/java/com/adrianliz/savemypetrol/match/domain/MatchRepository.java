package com.adrianliz.savemypetrol.match.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MatchRepository {

  Mono<FindMatchesProcess> findAll();

  Flux<Match> find(final FindMatchesProcess process);
}
