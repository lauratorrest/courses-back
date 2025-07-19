package com.company.coursya.service.impl;

import com.company.coursya.api.dto.authentication.RegisterResponse;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.User;
import com.company.coursya.repository.AuthenticationRepository;
import com.company.coursya.service.AuthenticationService;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.EmailAlreadyRegisteredException;
import com.company.coursya.shared.exceptions.exceptions.EmailNotRegisteredException;
import com.company.coursya.shared.exceptions.exceptions.InactiveUserException;
import com.company.coursya.shared.exceptions.exceptions.NotTheSameEmailException;
import com.company.coursya.shared.exceptions.exceptions.WrongPasswordException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse savePreRegisterData(String email, String confirmEmail, String fullName, String password) {
        if (!Objects.equals(email, confirmEmail)) {
            throw new NotTheSameEmailException(ExceptionCode.NOT_THE_SAME_EMAIL);
        }

        Boolean emailAlreadyRegistered = authenticationRepository.existsByEmail(email);
        if (Boolean.TRUE.equals(emailAlreadyRegistered)) {
            throw new EmailAlreadyRegisteredException(ExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        AuthenticationData authData = authenticationRepository.save(
                AuthenticationData.builder()
                        .email(email)
                        .active(Boolean.TRUE)
                        .password(passwordEncoder.encode(password))
                        .createdDate(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime())
                        .build());
        User savedUser = userService.saveUser(fullName, authData.getId());
        return buildRegisterResponse(authData.getEmail(), savedUser.getFullName());
    }

    @Override
    public RegisterResponse signInUser(String email, String password) {
        AuthenticationData authData = authenticationRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotRegisteredException(ExceptionCode.NOT_REGISTERED));

        if(authData.getActive() == Boolean.FALSE){
            throw new InactiveUserException(ExceptionCode.INACTIVE_USER);
        }

        if (!passwordEncoder.matches(password, authData.getPassword())) {
            throw new WrongPasswordException(ExceptionCode.WRONG_PASSWORD);
        }

        User userData = userService.findByAuthId(authData.getId());

        return buildRegisterResponse(authData.getEmail(), userData.getFullName());
    }

    private RegisterResponse buildRegisterResponse(String email, String fullName) {
        return RegisterResponse.builder()
                .email(email)
                .fullName(fullName)
                .build();
    }
}
