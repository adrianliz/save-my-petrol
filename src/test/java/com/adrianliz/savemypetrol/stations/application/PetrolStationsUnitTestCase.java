package com.adrianliz.savemypetrol.stations.application;

import static org.mockito.Mockito.mock;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import org.junit.jupiter.api.BeforeEach;

public abstract class PetrolStationsUnitTestCase {
  protected PetrolStationsRepository petrolStationRepository;

  @BeforeEach
  void setUp() {
    petrolStationRepository = mock(PetrolStationsRepository.class);
  }
}
