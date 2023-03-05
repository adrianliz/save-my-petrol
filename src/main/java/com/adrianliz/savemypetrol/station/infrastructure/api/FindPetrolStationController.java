package com.adrianliz.savemypetrol.station.infrastructure.api;

import com.adrianliz.savemypetrol.station.application.FindPetrolStationUseCase;
import com.adrianliz.savemypetrol.station.application.PetrolStationResponse;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class FindPetrolStationController implements PetrolStationsControllerV1 {

  private final FindPetrolStationUseCase findPetrolStationUseCase;

  @GetMapping("/{id}")
  public Mono<PetrolStationResponse> findById(@NotNull @Min(1) @PathVariable("id") final Long id) {
    return findPetrolStationUseCase.execute(new PetrolStationId(id));
  }
}
