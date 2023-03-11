package com.adrianliz.savemypetrol.product.infrastructure.api;

import com.adrianliz.savemypetrol.common.domain.Page;
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
  private final Integer maxPrices;

  public ProductFilter buildFilter() {
    return new ProductFilter.ProductFilterBuilder()
        .targetProductType(ProductType.findById(targetProductTypeId))
        .targetPetrolStationsIds(
            targetPetrolStationsIds.stream().map(PetrolStationId::new).collect(Collectors.toList()))
        .pageRequested(Page.of(maxPrices))
        .build();
  }
}
