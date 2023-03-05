package com.adrianliz.savemypetrol.station.application;

import com.adrianliz.savemypetrol.station.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.station.domain.PetrolStationRepository;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class FindPetrolStationsUseCase {

  private final PetrolStationRepository petrolStationRepository;

  public Flux<PetrolStationResponse> execute(
      @NotNull final PetrolStationFilter petrolStationFilter) {

    return petrolStationRepository.find(petrolStationFilter).map(PetrolStationResponse::from);
  }
}
