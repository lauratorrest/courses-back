package com.company.coursya.service;

import com.company.coursya.api.dto.authentication.RegisterResponse;
import com.company.coursya.api.dto.authentication.SignInResponse;
import com.company.coursya.model.AuthenticationData;

public interface AuthenticationService {

    RegisterResponse registerUser(String email,
                                  String confirmEmail,
                                  String fullName, String password);

    SignInResponse signInUser(String email, String password);

    AuthenticationData findByEmail(String email);
}
