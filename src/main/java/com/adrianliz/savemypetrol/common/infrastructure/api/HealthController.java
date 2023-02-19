package com.adrianliz.savemypetrol.common.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthController {

  @GetMapping(path = "/api/v1/health")
  public Mono<ResponseEntity<String>> health() {
    return Mono.just(ResponseEntity.ok().body("OK"));
  }
}
