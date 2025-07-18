package com.company.coursya.repository;

import com.company.coursya.model.AuthenticationData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthenticationRepository extends MongoRepository<AuthenticationData, String> {

    Boolean existsByEmail(String email);
}
