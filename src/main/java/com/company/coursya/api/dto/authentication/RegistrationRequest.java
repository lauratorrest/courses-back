package com.company.coursya.api.dto.authentication;

import com.company.coursya.model.enums.UserRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    @Schema(description = "UserData email")
    @NotBlank(message = "UserData email cannot be blank")
    @Size(max = 100, message = "UserData email must be {max} characters or less")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String email;

    @Schema(description = "UserData confirmation email")
    @NotBlank(message = "UserData confirmation email cannot be blank")
    @Size(max = 100, message = "UserData confirmation email must be {max} characters or less")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String confirmEmail;

    @Schema(description = "UserData name")
    @NotBlank(message = "UserData name cannot be blank")
    @Size(min = 5, max = 100, message = "UserData name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "UserData name can only contain letters and spaces")
    private String fullName;

    @Schema(description = "UserData password")
    @Size(min = 8, message = "UserData password must be {min} characters or more")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "La contraseña debe tener al menos una mayúscula, un número y mínimo 8 caracteres"
    )
    private String password;

    private UserRoleEnum role;
}
