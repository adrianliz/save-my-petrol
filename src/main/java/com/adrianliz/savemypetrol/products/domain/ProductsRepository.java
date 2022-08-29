package com.adrianliz.savemypetrol.products.domain;

import reactor.core.publisher.Flux;

public interface ProductsRepository {
  Flux<Product> find(final ProductFilter filter);
}
