package com.company.coursya.service;

import com.company.coursya.api.dto.authentication.PreRegisterResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public interface AuthenticationService {

    PreRegisterResponse savePreRegisterData(@NotBlank(message = "User email cannot be blank") @Size(max = 100, message = "User email must be {max} characters or less") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format") String email, @NotBlank(message = "User confirmation email cannot be blank") @Size(max = 100, message = "User confirmation email must be {max} characters or less") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format") String confirmEmail, @NotBlank(message = "User name cannot be blank") @Size(min = 5, max = 100, message = "User name must be between {min} and {max} characters") @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "User name can only contain letters and spaces") String fullName);
}
