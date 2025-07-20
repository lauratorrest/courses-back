package com.company.coursya.api.dto.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponse {

    private String email;
    private String fullName;
    private String token;
}
