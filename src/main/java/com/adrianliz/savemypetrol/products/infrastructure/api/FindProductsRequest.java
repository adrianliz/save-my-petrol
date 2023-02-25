package com.adrianliz.savemypetrol.products.infrastructure.api;

import com.adrianliz.savemypetrol.products.domain.ProductFilter;
import com.adrianliz.savemypetrol.products.domain.ProductType;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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
