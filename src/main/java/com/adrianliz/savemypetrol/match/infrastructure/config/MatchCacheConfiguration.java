package com.adrianliz.savemypetrol.match.infrastructure.config;

import com.adrianliz.savemypetrol.match.domain.FindMatchesProcess;
import com.adrianliz.savemypetrol.match.domain.Match;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchCacheConfiguration {

  public static final String MATCHES_CACHE = "matchesCache";

  @Bean
  public IMap<FindMatchesProcess, List<Match>> matchesCache(
      final HazelcastInstance hazelcastInstance) {

    hazelcastInstance
        .getConfig()
        .getMapConfig(MATCHES_CACHE)
        .setTimeToLiveSeconds(300); // NOTE: 5 minutes
    return hazelcastInstance.getMap(MATCHES_CACHE);
  }
}
