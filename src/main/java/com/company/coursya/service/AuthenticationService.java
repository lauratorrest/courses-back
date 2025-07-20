package com.company.coursya.service;

import com.company.coursya.api.dto.authentication.RegisterResponse;
import com.company.coursya.api.dto.authentication.SignInResponse;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.enums.UserRoleEnum;

public interface AuthenticationService {

    RegisterResponse registerUser(String email,
                                  String confirmEmail,
                                  String fullName,
                                  String password,
                                  UserRoleEnum role);

    SignInResponse signInUser(String email, String password);

    AuthenticationData findByEmail(String email);
}
