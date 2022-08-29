package com.adrianliz.savemypetrol.products.infrastructure;

import com.adrianliz.savemypetrol.products.domain.Product;
import com.adrianliz.savemypetrol.products.domain.ProductFilter;
import com.adrianliz.savemypetrol.products.domain.ProductsRepository;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@AllArgsConstructor
public class ProductsProxyRepository implements ProductsRepository {
  private final PetrolStationsRepository petrolStationsRepository;

  @Override
  public Flux<Product> find(final ProductFilter filter) {
    return filter.applyTo(filter.searchTargetProducts(petrolStationsRepository));
  }
}
