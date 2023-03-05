package com.adrianliz.savemypetrol.station.application;

import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import com.adrianliz.savemypetrol.station.domain.PetrolStationRepository;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FindPetrolStationUseCase {

  private final PetrolStationRepository petrolStationRepository;

  public Mono<PetrolStationResponse> execute(
      @NotNull @Valid final PetrolStationId petrolStationId) {

    return petrolStationRepository.findById(petrolStationId).map(PetrolStationResponse::from);
  }
}
