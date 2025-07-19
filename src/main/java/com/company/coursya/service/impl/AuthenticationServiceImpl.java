package com.company.coursya.service.impl;

import com.company.coursya.api.dto.authentication.RegisterResponse;
import com.company.coursya.api.dto.authentication.SignInResponse;
import com.company.coursya.jwt.JwtService;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.UserData;
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
    private final JwtService jwtService;

    @Override
    public RegisterResponse registerUser(String email, String confirmEmail, String fullName, String password) {
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
        UserData savedUserData = userService.saveUser(fullName, authData.getId());

        String token = jwtService.generateToken(authData.getEmail(), "admin");
        return buildRegisterResponse(authData.getEmail(), savedUserData.getFullName(), token);
    }

    private RegisterResponse buildRegisterResponse(String email, String fullName, String token) {
        return RegisterResponse.builder()
                .email(email)
                .fullName(fullName)
                .token(token)
                .build();
    }

    @Override
    public SignInResponse signInUser(String email, String password) {
        AuthenticationData authData = getAuthDataByEmail(email);

        if(authData.getActive() == Boolean.FALSE){
            throw new InactiveUserException(ExceptionCode.INACTIVE_USER);
        }

        if (!passwordEncoder.matches(password, authData.getPassword())) {
            throw new WrongPasswordException(ExceptionCode.WRONG_PASSWORD);
        }

        UserData userData = userService.findByAuthId(authData.getId());
        String token = jwtService.generateToken(authData.getEmail(), "admin");

        return buildSignInResponse(authData.getEmail(), userData.getFullName(), token);
    }

    private SignInResponse buildSignInResponse(String email, String fullName, String token) {
        return SignInResponse.builder()
                .email(email)
                .fullName(fullName)
                .token(token)
                .build();
    }

    private AuthenticationData getAuthDataByEmail(String email) {
        return authenticationRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotRegisteredException(ExceptionCode.NOT_REGISTERED));
    }

    @Override
    public AuthenticationData findByEmail(String email) {
        return getAuthDataByEmail(email);
    }

}
