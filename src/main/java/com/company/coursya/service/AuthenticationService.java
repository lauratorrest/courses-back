package com.company.coursya.service;

import com.company.coursya.api.dto.authentication.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse savePreRegisterData(String email,
                                         String confirmEmail,
                                         String fullName, String password);
}
