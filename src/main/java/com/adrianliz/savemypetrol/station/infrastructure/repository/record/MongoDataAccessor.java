package com.adrianliz.savemypetrol.station.infrastructure.repository.record;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDataAccessor extends ReactiveMongoRepository<PetrolStationRecord, Long> {}
