package com.adrianliz.savemypetrol.match.infrastructure.repository;

import com.adrianliz.savemypetrol.app.config.CacheConfiguration;
import com.adrianliz.savemypetrol.common.domain.Currency;
import com.adrianliz.savemypetrol.common.domain.DoubleMother;
import com.adrianliz.savemypetrol.common.domain.LongMother;
import com.adrianliz.savemypetrol.common.infrastructure.MongoTestConfiguration;
import com.adrianliz.savemypetrol.match.infrastructure.config.MatchCacheConfiguration;
import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationIdMother;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocationMother;
import com.adrianliz.savemypetrol.station.domain.PetrolStationMother;
import com.adrianliz.savemypetrol.station.domain.PetrolStationNameMother;
import com.adrianliz.savemypetrol.station.domain.PetrolStationProductMother;
import com.adrianliz.savemypetrol.station.domain.ProductPriceMother;
import com.adrianliz.savemypetrol.station.infrastructure.config.PetrolStationsCacheConfiguration;
import com.adrianliz.savemypetrol.station.infrastructure.repository.MongoPetrolStationStorage;
import com.adrianliz.savemypetrol.trigger.domain.Trigger;
import com.adrianliz.savemypetrol.trigger.domain.TriggerIdMother;
import com.adrianliz.savemypetrol.trigger.domain.TriggerMother;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetProductMother;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetProductPriceMother;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserDistanceMother;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserIdMother;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserLocationMother;
import com.adrianliz.savemypetrol.trigger.domain.TriggerTargetUserMother;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.MongoTriggerStorage;
import com.adrianliz.savemypetrol.trigger.infrastructure.repository.record.TriggerRecord;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;

@Profile("test")
@SpringBootTest(
    classes = {
      CacheConfiguration.class,
      MatchCacheConfiguration.class,
      PetrolStationsCacheConfiguration.class,
      MongoTestConfiguration.class,
      MongoPetrolStationStorage.class,
      MongoTriggerStorage.class,
      MatchProxyRepository.class,
    })
public class MatchProxyRepositoryTestCase {

  @Autowired private ReactiveMongoTemplate dataAccessor;
  @Autowired private MongoTriggerStorage storage;

  @Autowired protected MatchProxyRepository repository;

  @BeforeAll
  static void beforeAll() {
    System.setProperty("logging.level.org.springframework.data.mongodb.core", "DEBUG");
  }

  @BeforeEach
  void setUp() {
    dataAccessor.dropCollection(TriggerRecord.class).block();
    dataAccessor
        .indexOps(TriggerRecord.class)
        .ensureIndex(new GeospatialIndex("sourceLocation").typed(GeoSpatialIndexType.GEO_2DSPHERE))
        .block();
  }

  @AfterEach
  void tearDown() {
    dataAccessor.dropCollection(TriggerRecord.class).block();
  }

  Trigger givenThereIsRandomTrigger() {
    final var trigger =
        TriggerMother.create(
            TriggerIdMother.random(),
            TriggerTargetUserMother.create(
                TriggerTargetUserIdMother.random(),
                TriggerTargetUserLocationMother.random(),
                TriggerTargetUserDistanceMother.create(LongMother.randomBetween(5000L, 25000L))),
            TriggerTargetProductMother.create(
                ProductType.REGULAR_95,
                TriggerTargetProductPriceMother.create(LongMother.randomBetween(90L, 2000L))));
    storage.save(trigger).block();
    return trigger;
  }

  PetrolStation givenThereIsRandomPetrolStationThatMatch(final Trigger trigger) {
    final var targetUser = trigger.targetUser();
    final var targetUserLocation = targetUser.sourceLocation();
    final var targetProduct = trigger.targetProduct();

    final var petrolStationsLocation =
        PetrolStationLocationMother.move(
            new PetrolStationLocation(
                targetUserLocation.latitude(), targetUserLocation.longitude()),
            DoubleMother.randomBetween(0L, 1000L));
    final var petrolStationProduct =
        PetrolStationProductMother.create(
            targetProduct.type(),
            ProductPriceMother.create(
                LongMother.randomBetween(1L, targetProduct.price().cents()), Currency.EUR));

    return PetrolStationMother.create(
        PetrolStationIdMother.random(),
        PetrolStationNameMother.random(),
        petrolStationsLocation,
        List.of(petrolStationProduct));
  }

  PetrolStation givenThereIsRandomPetrolStationFarAway(final Trigger trigger) {
    final var targetUser = trigger.targetUser();
    final var targetUserLocation = targetUser.sourceLocation();
    final var targetUserMaxDistance = targetUser.maxDistanceFromSource();

    final var petrolStationsLocation =
        PetrolStationLocationMother.move(
            new PetrolStationLocation(
                targetUserLocation.latitude(), targetUserLocation.longitude()),
            DoubleMother.randomBetween(targetUserMaxDistance.meters() + 1L, 100000L));
    final var petrolStationProduct =
        PetrolStationProductMother.create(
            ProductType.REGULAR_95,
            ProductPriceMother.create(LongMother.randomBetween(1L, 1000L), Currency.EUR));

    return PetrolStationMother.create(
        PetrolStationIdMother.random(),
        PetrolStationNameMother.random(),
        petrolStationsLocation,
        List.of(petrolStationProduct));
  }
}
