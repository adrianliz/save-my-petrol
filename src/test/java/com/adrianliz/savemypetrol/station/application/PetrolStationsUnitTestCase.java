package com.adrianliz.savemypetrol.station.application;

import static org.mockito.Mockito.mock;

import com.adrianliz.savemypetrol.station.domain.PetrolStationRepository;
import org.junit.jupiter.api.BeforeEach;

public abstract class PetrolStationsUnitTestCase {

  protected PetrolStationRepository petrolStationRepository;

  @BeforeEach
  void setUp() {
    petrolStationRepository = mock(PetrolStationRepository.class);
  }
}
