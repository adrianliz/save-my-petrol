package com.adrianliz.savemypetrol.product.infrastructure;

import com.adrianliz.savemypetrol.product.domain.Product;
import com.adrianliz.savemypetrol.product.domain.ProductFilter;
import com.adrianliz.savemypetrol.product.domain.ProductRepository;
import com.adrianliz.savemypetrol.station.domain.PetrolStationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@AllArgsConstructor
public class ProductProxyRepository implements ProductRepository {
  private final PetrolStationRepository petrolStationRepository;

  @Override
  public Flux<Product> find(final ProductFilter filter) {
    return filter.applyTo(filter.searchTargetProducts(petrolStationRepository));
  }
}
