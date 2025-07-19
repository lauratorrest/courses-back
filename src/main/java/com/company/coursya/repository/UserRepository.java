package com.company.coursya.repository;

import com.company.coursya.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByAuthId(String userId);
}
