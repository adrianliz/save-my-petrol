package com.adrianliz.savemypetrol.app.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.UserCodeDeploymentConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

  @Bean
  public CacheManager cacheManager() {
    return new com.hazelcast.spring.cache.HazelcastCacheManager(hazelcastInstance());
  }

  @Bean
  public Config config() {
    final var config = new Config();
    config.setClassLoader(Thread.currentThread().getContextClassLoader());
    config.setClusterName("save-my-petrol-cluster");
    config.setProperty("hazelcast.phone.home.enabled", "false");
    config.setProperty("hazelcast.discovery.enabled", "true");

    final var codeDeploymentConfig = config.getUserCodeDeploymentConfig();
    codeDeploymentConfig
        .setEnabled(true)
        .setClassCacheMode(UserCodeDeploymentConfig.ClassCacheMode.ETERNAL)
        .setProviderMode(UserCodeDeploymentConfig.ProviderMode.LOCAL_CLASSES_ONLY);

    return config;
  }

  @Bean
  public HazelcastInstance hazelcastInstance() {
    final var config = config();

    return Hazelcast.newHazelcastInstance(config);
  }
}
