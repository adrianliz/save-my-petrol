package com.adrianliz.savemypetrol.station.infrastructure.api;

import com.adrianliz.savemypetrol.station.application.FindPetrolStationsUseCase;
import com.adrianliz.savemypetrol.station.application.PetrolStationResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class FindPetrolStationsController implements PetrolStationsControllerV1 {

  private final FindPetrolStationsUseCase findPetrolStationsUseCase;

  @GetMapping("/search")
  public Flux<PetrolStationResponse> find(
      @NotNull @Valid final FindPetrolStationsRequest findPetrolStationsRequest) {

    return findPetrolStationsUseCase.execute(findPetrolStationsRequest.buildFilter());
  }
}
