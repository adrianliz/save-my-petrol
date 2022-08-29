package com.adrianliz.savemypetrol.products.infrastructure.api;

import com.adrianliz.savemypetrol.products.application.FindProductsUseCase;
import com.adrianliz.savemypetrol.products.application.ProductResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class FindProductsController implements ProductsControllerV1 {
  private final FindProductsUseCase findProductsUseCase;

  @GetMapping("/search")
  public Flux<ProductResponse> find(@NotNull @Valid final FindProductsRequest findProductsRequest) {
    return findProductsUseCase.execute(findProductsRequest.buildFilter());
  }
}
