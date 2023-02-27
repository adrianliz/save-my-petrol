package com.adrianliz.savemypetrol.station.infrastructure.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true) // NOTE: Needed for Jackson
@Getter
public class PetrolStationsResponse {

  @JsonProperty("ListaEESSPrecio")
  private final List<PetrolStationResponse> stations;
}
