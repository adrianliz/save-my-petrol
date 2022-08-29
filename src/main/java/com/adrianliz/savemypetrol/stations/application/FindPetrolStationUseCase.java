package com.adrianliz.savemypetrol.stations.application;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FindPetrolStationUseCase {
  private final PetrolStationsRepository petrolStationsRepository;

  public Mono<PetrolStationResponse> execute(
      @NotNull @Valid final PetrolStationId petrolStationId) {

    return petrolStationsRepository.findById(petrolStationId).map(PetrolStationResponse::from);
  }
}
