package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import com.mongodb.ConnectionString;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class MongoTestConfiguration extends AbstractReactiveMongoConfiguration {
  private static final MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:5.0.12");

  static {
    mongoDbContainer.start();
  }

  @Bean
  @Primary
  ReactiveMongoDatabaseFactory mongoClientFactory() {
    return new SimpleReactiveMongoDatabaseFactory(
        new ConnectionString(mongoDbContainer.getReplicaSetUrl("test")));
  }

  @Override
  protected String getDatabaseName() {
    return "test";
  }

  @Override
  public boolean autoIndexCreation() {
    return true;
  }
}
