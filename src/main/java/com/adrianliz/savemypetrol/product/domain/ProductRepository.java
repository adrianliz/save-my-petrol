package com.adrianliz.savemypetrol.product.domain;

import reactor.core.publisher.Flux;

public interface ProductRepository {

  Flux<Product> find(final ProductFilter filter);
}
