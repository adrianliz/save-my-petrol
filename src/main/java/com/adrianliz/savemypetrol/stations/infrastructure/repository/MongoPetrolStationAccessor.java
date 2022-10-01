package com.adrianliz.savemypetrol.stations.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoPetrolStationAccessor
    extends ReactiveMongoRepository<PetrolStationRecord, Long> {}
