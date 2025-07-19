package com.company.coursya.repository;

import com.company.coursya.model.AuthenticationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthenticationRepository extends MongoRepository<AuthenticationData, String> {

    Boolean existsByEmail(String email);

    Optional<AuthenticationData> findByEmail(String email);
}
