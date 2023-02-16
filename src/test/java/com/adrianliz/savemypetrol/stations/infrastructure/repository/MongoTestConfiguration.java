package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.mongodb.ConnectionString;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@TestConfiguration
public class MongoTestConfiguration extends AbstractReactiveMongoConfiguration {
  private static final MongoDBContainer mongoDbContainer =
      new MongoDBContainer("mongo:5.0.14").waitingFor(Wait.forListeningPort());

  @Bean
  @Primary
  ReactiveMongoDatabaseFactory mongoClientFactory() {
    return new SimpleReactiveMongoDatabaseFactory(
        new ConnectionString(mongoDbContainer.getReplicaSetUrl("test")));
  }

  @PostConstruct
  private void init() {
    mongoDbContainer.start();
  }

  @PreDestroy
  private void destroy() {
    mongoDbContainer.stop();
  }

  @Override
  protected @NotNull String getDatabaseName() {
    return "test";
  }

  @Override
  public boolean autoIndexCreation() {
    return true;
  }
}
