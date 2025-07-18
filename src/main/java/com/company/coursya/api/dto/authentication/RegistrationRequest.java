package com.company.coursya.api.dto.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    @Schema(description = "User email")
    @NotBlank(message = "User email cannot be blank")
    @Size(max = 100, message = "User email must be {max} characters or less")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String email;

    @Schema(description = "User confirmation email")
    @NotBlank(message = "User confirmation email cannot be blank")
    @Size(max = 100, message = "User confirmation email must be {max} characters or less")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String confirmEmail;

    @Schema(description = "User name")
    @NotBlank(message = "User name cannot be blank")
    @Size(min = 5, max = 100, message = "User name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "User name can only contain letters and spaces")
    private String fullName;

    @Schema(description = "User password")
    @Size(min = 8, message = "User password must be {min} characters or more")
    @Pattern(regexp = "^(?=.*[A-Za-z0-9@#$%^&+=*]).{8,100}$",
            message = "Password must contain letters, numbers and special characters, and be at least 8 characters long")
    private String password;
}
