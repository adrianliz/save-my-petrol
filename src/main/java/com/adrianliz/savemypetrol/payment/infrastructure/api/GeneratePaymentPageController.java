package com.adrianliz.savemypetrol.payment.infrastructure.api;

import com.adrianliz.savemypetrol.payment.application.GeneratePaymentPageUseCase;
import com.adrianliz.savemypetrol.payment.application.PaymentPageResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true")
@RestController
@AllArgsConstructor
public final class GeneratePaymentPageController implements PaymentsControllerV1 {

  private final GeneratePaymentPageUseCase generatePaymentPageUseCase;

  @PostMapping("/payment-page")
  public Mono<PaymentPageResponse> generatePaymentPage(
      @RequestBody @NotNull @Valid final GeneratePaymentPageRequest generatePaymentPageRequest) {

    return generatePaymentPageUseCase.execute(generatePaymentPageRequest.buildPaymentUserId());
  }
}
