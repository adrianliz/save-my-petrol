package com.adrianliz.savemypetrol.common.infrastructure.api;

import com.adrianliz.savemypetrol.common.domain.SaveMyPetrolException;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

@Service
public final class HttpErrorHandler extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(
      final ServerRequest request, final ErrorAttributeOptions options) {

    final var attributes = super.getErrorAttributes(request, options);
    final var error = getError(request);
    if (error instanceof SaveMyPetrolException) {
      final var internalError = ((SaveMyPetrolException) error).toResponseStatusException();
      attributes.put("status", internalError.getRawStatusCode());
      attributes.put("error", internalError.getStatus().getReasonPhrase());
      attributes.put("message", internalError.getMessage());
      return attributes;
    }
    return attributes;
  }
}
