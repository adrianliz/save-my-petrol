package com.adrianliz.savemypetrol.stations.infrastructure.config;

import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetrolStationsCacheConfiguration {

  public static final String PETROL_STATIONS_CACHE = "petrolStationsCache";

  @Bean
  public IMap<PetrolStationId, PetrolStation> petrolStationsCache(
      final HazelcastInstance hazelcastInstance) {

    hazelcastInstance.getConfig().getMapConfig(PETROL_STATIONS_CACHE).setTimeToLiveSeconds(1800);
    return hazelcastInstance.getMap(PETROL_STATIONS_CACHE);
  }
}
