package com.example.CRUD.repository;

import com.example.CRUD.model.Spartan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpartanRepository extends MongoRepository<Spartan, String> {

}
