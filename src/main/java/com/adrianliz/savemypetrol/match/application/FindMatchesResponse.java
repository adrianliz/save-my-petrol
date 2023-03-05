package com.adrianliz.savemypetrol.match.application;

import com.adrianliz.savemypetrol.match.domain.FindMatchesProcess;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class FindMatchesResponse {

  private final String processId;

  public static FindMatchesResponse from(final FindMatchesProcess process) {
    return new FindMatchesResponse(process.id().valueAsString());
  }
}
