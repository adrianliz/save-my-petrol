package com.adrianliz.savemypetrol.station.infrastructure.config;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import javax.net.ssl.SSLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class PetrolStationsWebClientConfiguration {

  @Value("${app.petrolStationsEndpoint}")
  private String petrolStationsEndpoint;

  @Value("${spring.codec.max-in-memory-size}")
  private DataSize codecMaxInMemorySize;

  @Bean
  public WebClient webClient() throws SSLException {
    final var sslContext =
        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();

    final var httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

    return WebClient.builder()
        .baseUrl(petrolStationsEndpoint)
        .codecs(
            configurer -> {
              configurer.defaultCodecs().maxInMemorySize((int) codecMaxInMemorySize.toBytes());
              configurer.defaultCodecs().enableLoggingRequestDetails(true);
            })
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .build();
  }
}
