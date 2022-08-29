package com.adrianliz.savemypetrol.products.domain;

import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Builder
public class ProductFilter {
  private final List<PetrolStationId> targetPetrolStationsIds;
  private final ProductType targetType;

  private static final Comparator<Product> PRODUCT_COMPARATOR =
      Comparator.nullsFirst(Product::compareTo);

  public Flux<Product> applyTo(final Flux<Product> products) {
    return products
        .filter(product -> product.getType().equals(targetType))
        .sort(PRODUCT_COMPARATOR);
  }

  public Flux<Product> searchTargetProducts(final PetrolStationsRepository repository) {
    return Flux.concat(
            targetPetrolStationsIds.stream().map(repository::findById).collect(Collectors.toList()))
        .flatMapIterable(
            petrolStation ->
                petrolStation.getProducts().stream()
                    .map(
                        petrolStationProduct ->
                            new Product(
                                petrolStation.getId(),
                                petrolStationProduct.getType(),
                                petrolStationProduct.getPrice()))
                    .collect(Collectors.toList()));
  }
}
