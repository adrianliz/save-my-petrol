package com.adrianliz.savemypetrol.match.infrastructure.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.adrianliz.savemypetrol.match.domain.Match;
import com.adrianliz.savemypetrol.match.domain.PetrolStationMatch;
import com.adrianliz.savemypetrol.match.domain.UserMatch;
import org.junit.jupiter.api.Test;

public final class FindMatchesFromPetrolStationShould extends MatchProxyRepositoryTestCase {

  @Test
  void find_zero_matches_if_petrol_station_is_far_away() {
    final var givenTrigger = givenThereIsRandomTrigger();
    final var givenPetrolStation = givenThereIsRandomPetrolStationFarAway(givenTrigger);

    final var match = repository.findMatchesFrom(givenPetrolStation).collectList().block();

    assert match != null;
    assertThat(match.size()).isEqualTo(0);
  }

  @Test
  void find_all() {
    final var givenTrigger = givenThereIsRandomTrigger();
    final var givenPetrolStation = givenThereIsRandomPetrolStationThatMatch(givenTrigger);

    final var match = repository.findMatchesFrom(givenPetrolStation).collectList().block();

    assert match != null;
    assertThat(match.size()).isEqualTo(1);
    assertThat(match.get(0))
        .isEqualTo(
            new Match(
                givenTrigger.id(),
                new UserMatch(givenTrigger.targetUser().id()),
                new PetrolStationMatch(
                    givenPetrolStation.id(),
                    givenPetrolStation.name(),
                    givenPetrolStation.location(),
                    givenPetrolStation.products().stream()
                        .filter(
                            product -> product.type().equals(givenTrigger.targetProduct().type()))
                        .findFirst()
                        .orElse(null))));
  }
}
