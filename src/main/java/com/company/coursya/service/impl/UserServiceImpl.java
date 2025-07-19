package com.company.coursya.service.impl;

import com.company.coursya.model.User;
import com.company.coursya.repository.UserRepository;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(String fullName, String authId) {
        return userRepository.save(User.builder().fullName(fullName).authId(authId).build());
    }

    @Override
    public User findByAuthId(String userId) {
        return userRepository.findByAuthId(userId).orElseThrow(() -> new UserNotFoundException(ExceptionCode.USER_NOT_FOUND));
    }
}
