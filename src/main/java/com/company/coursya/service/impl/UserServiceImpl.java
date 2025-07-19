package com.company.coursya.service.impl;

import com.company.coursya.api.dto.user.UserInfoResponse;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.User;
import com.company.coursya.repository.UserRepository;
import com.company.coursya.service.AuthenticationService;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.UserNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public UserServiceImpl(
            @Lazy AuthenticationService authenticationService,
            UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(String fullName, String authId) {
        return userRepository.save(User.builder().fullName(fullName).authId(authId).build());
    }

    @Override
    public User findByAuthId(String userId) {
        return userRepository.findByAuthId(userId).orElseThrow(() -> new UserNotFoundException(ExceptionCode.USER_NOT_FOUND));
    }

    @Override
    public UserInfoResponse findByEmail(String email) {
        AuthenticationData authData = authenticationService.findByEmail(email);
        User foundUser = findByAuthId(authData.getId());
        return UserInfoResponse.builder().fullName(foundUser.getFullName()).build();
    }
}
