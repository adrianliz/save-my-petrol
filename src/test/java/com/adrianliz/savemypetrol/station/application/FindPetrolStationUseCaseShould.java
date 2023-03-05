package com.adrianliz.savemypetrol.station.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.adrianliz.savemypetrol.station.domain.PetrolStationIdMother;
import com.adrianliz.savemypetrol.station.domain.PetrolStationMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FindPetrolStationUseCaseShould extends PetrolStationsUnitTestCase {

  private FindPetrolStationUseCase findPetrolStationUseCase;

  @Override
  @BeforeEach
  protected void setUp() {
    super.setUp();
    findPetrolStationUseCase = new FindPetrolStationUseCase(petrolStationRepository);
  }

  @Test
  void find_an_existing_petrol_station() {
    final var petrolStationId = PetrolStationIdMother.random();
    final var expectedPetrolStation = PetrolStationMother.randomWithoutProducts();
    final var expectedPetrolStationResponse =
        PetrolStationResponseMother.create(expectedPetrolStation);

    when(petrolStationRepository.findById(petrolStationId))
        .thenReturn(Mono.just(expectedPetrolStation));

    final var petrolStationResponse = findPetrolStationUseCase.execute(petrolStationId);

    StepVerifier.create(petrolStationResponse)
        .assertNext(
            response -> {
              assertNotNull(response);
              assertEquals(response, expectedPetrolStationResponse);
            })
        .verifyComplete();
  }

  @Test
  void return_empty_stream_on_nonexistent_petrol_station() {
    final var petrolStationId = PetrolStationIdMother.random();

    when(petrolStationRepository.findById(petrolStationId)).thenReturn(Mono.empty());

    final var petrolStationResponse = findPetrolStationUseCase.execute(petrolStationId);

    StepVerifier.create(petrolStationResponse).verifyComplete();
  }
}
