package com.adrianliz.savemypetrol.product.application;

import com.adrianliz.savemypetrol.product.domain.ProductFilter;
import com.adrianliz.savemypetrol.product.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class FindProductsUseCase {
  private final ProductRepository productRepository;

  public Flux<ProductResponse> execute(final ProductFilter filter) {
    return productRepository.find(filter).map(ProductResponse::from);
  }
}
