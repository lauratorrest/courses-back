package com.company.coursya.service.impl;

import com.company.coursya.model.User;
import com.company.coursya.repository.UserRepository;
import com.company.coursya.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(String fullName) {
        return userRepository.save(User.builder().fullName(fullName).build());
    }
}
