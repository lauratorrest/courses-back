package com.company.coursya.service.impl;

import com.company.coursya.api.dto.user.UserInfoResponse;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.UserData;
import com.company.coursya.repository.UserRepository;
import com.company.coursya.service.AuthenticationService;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.UserNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public UserData saveUser(String fullName, String authId) {
        return userRepository.save(UserData.builder().fullName(fullName).authId(authId).build());
    }

    @Override
    public UserData findByAuthId(String authId) {
        return userRepository.findByAuthId(authId).orElseThrow(() -> new UserNotFoundException(ExceptionCode.USER_NOT_FOUND));
    }

    @Override
    public UserInfoResponse findByEmail(String email) {
        AuthenticationData authenticationData = getAuthDataByEmail(email);
        UserData foundUserData = findByAuthId(authenticationData.getId());
        return UserInfoResponse.builder().fullName(foundUserData.getFullName()).build();
    }

    private AuthenticationData getAuthDataByEmail(String email) {
        return authenticationService.findByEmail(email);
    }

    @Override
    public UserDetails findUserDetailsByEmail(String email) {
        AuthenticationData authenticationData = getAuthDataByEmail(email);
        return new User(authenticationData.getEmail(), authenticationData.getPassword(), new ArrayList<>());
    }
}
