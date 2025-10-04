package com.example.gym.access;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends MongoRepository<AccessRecord, String> {
}
