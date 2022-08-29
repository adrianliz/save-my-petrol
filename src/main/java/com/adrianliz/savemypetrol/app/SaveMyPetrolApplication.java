package com.adrianliz.savemypetrol.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.adrianliz")
public class SaveMyPetrolApplication {

  public static void main(final String[] args) {
    SpringApplication.run(SaveMyPetrolApplication.class, args);
  }
}
