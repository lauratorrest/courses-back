package com.company.coursya.repository;

import com.company.coursya.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserData, String> {

    Optional<UserData> findByAuthId(String userId);
}
