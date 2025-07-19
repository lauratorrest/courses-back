package com.company.coursya.api.dto.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

    @Schema(description = "User email")
    @NotBlank(message = "User email cannot be blank")
    @Size(max = 100, message = "User email must be {max} characters or less")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String email;

    @Schema(description = "User password")
    @Size(min = 8, message = "User password must be {min} characters or more")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "La contraseña debe tener al menos una mayúscula, un número y mínimo 8 caracteres"
    )
    private String password;
}
