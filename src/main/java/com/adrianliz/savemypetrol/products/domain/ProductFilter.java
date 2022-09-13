package com.adrianliz.savemypetrol.products.domain;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;

public class ProductFilter {
  private static final Comparator<Product> PRODUCT_COMPARATOR =
      Comparator.nullsFirst(Product::compareTo);

  private final List<PetrolStationId> targetPetrolStationsIds;
  private final ProductType targetProductType;

  public ProductFilter(
      final List<PetrolStationId> targetPetrolStationsIds, final ProductType targetProductType) {

    this.targetPetrolStationsIds = targetPetrolStationsIds;
    this.targetProductType = targetProductType;
  }

  public Flux<Product> applyTo(final Flux<Product> products) {
    return products.filter(product -> product.hasType(targetProductType)).sort(PRODUCT_COMPARATOR);
  }

  public Flux<Product> searchTargetProducts(final PetrolStationsRepository repository) {
    return Flux.concat(
            targetPetrolStationsIds.stream().map(repository::findById).collect(Collectors.toList()))
        .flatMapIterable(
            petrolStation ->
                petrolStation.products().stream()
                    .map(
                        petrolStationProduct ->
                            new Product(
                                petrolStation.id(),
                                petrolStationProduct.type(),
                                petrolStationProduct.price()))
                    .collect(Collectors.toList()));
  }

  public static class ProductFilterBuilder {
    private List<PetrolStationId> targetPetrolStationsIds;
    private ProductType targetProductType;

    public ProductFilterBuilder() {}

    public ProductFilterBuilder targetPetrolStationsIds(
        final List<PetrolStationId> targetPetrolStationsIds) {

      this.targetPetrolStationsIds = targetPetrolStationsIds;
      return this;
    }

    public ProductFilterBuilder targetProductType(final ProductType targetProductType) {
      this.targetProductType = targetProductType;
      return this;
    }

    public ProductFilter build() {
      return new ProductFilter(targetPetrolStationsIds, targetProductType);
    }
  }
}
