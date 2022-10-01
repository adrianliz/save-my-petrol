package com.adrianliz.savemypetrol.stations.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PetrolStationsWebClientConfiguration {

  @Value("${app.petrolStationsEndpoint}")
  private String petrolStationsEndpoint;

  @Value("${spring.codec.max-in-memory-size}")
  private DataSize codecMaxInMemorySize;

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .baseUrl(petrolStationsEndpoint)
        .codecs(
            configurer -> {
              configurer.defaultCodecs().maxInMemorySize((int) codecMaxInMemorySize.toBytes());
              configurer.defaultCodecs().enableLoggingRequestDetails(true);
            })
        .build();
  }
}
