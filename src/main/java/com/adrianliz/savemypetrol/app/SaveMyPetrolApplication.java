package com.adrianliz.savemypetrol.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.adrianliz")
@EnableReactiveMongoRepositories(basePackages = "com.adrianliz")
@EnableScheduling
public class SaveMyPetrolApplication {
  public static void main(final String[] args) {
    SpringApplication.run(SaveMyPetrolApplication.class, args);
  }
}
