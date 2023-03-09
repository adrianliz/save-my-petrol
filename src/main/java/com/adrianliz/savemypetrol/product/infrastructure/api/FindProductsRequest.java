package com.adrianliz.savemypetrol.product.infrastructure.api;

import com.adrianliz.savemypetrol.product.domain.ProductFilter;
import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Jacksonized
@Builder
@Getter
public class FindProductsRequest {

  @NotEmpty @Valid private final List<@NotNull Long> targetPetrolStationsIds;
  @NotNull private final Integer targetProductTypeId;

  public ProductFilter buildFilter() {
    final var filter = new ProductFilter.ProductFilterBuilder();
    filter.targetProductType(ProductType.findById(targetProductTypeId));
    filter.targetPetrolStationsIds(
        targetPetrolStationsIds.stream().map(PetrolStationId::new).collect(Collectors.toList()));

    return filter.build();
  }
}
