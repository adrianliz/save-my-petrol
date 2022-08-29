package com.adrianliz.savemypetrol.products.application;

import com.adrianliz.savemypetrol.products.domain.ProductFilter;
import com.adrianliz.savemypetrol.products.domain.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class FindProductsUseCase {
  private final ProductsRepository productsRepository;

  public Flux<ProductResponse> execute(final ProductFilter filter) {
    return productsRepository.find(filter).map(ProductResponse::from);
  }
}
