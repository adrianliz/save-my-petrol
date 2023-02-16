package com.adrianliz.savemypetrol.stations.application;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationFilter;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class FindPetrolStationsUseCase {
  private final PetrolStationsRepository petrolStationsRepository;

  public Flux<PetrolStationResponse> execute(
      @NotNull final PetrolStationFilter petrolStationFilter) {

    return petrolStationsRepository.find(petrolStationFilter).map(PetrolStationResponse::from);
  }
}
