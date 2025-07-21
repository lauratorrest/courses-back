package com.company.coursya.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserDetailsRequest {

    private String email;
    @Schema(description = "UserData name")
    @NotBlank(message = "UserData name cannot be blank")
    @Size(min = 5, max = 100, message = "UserData name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "UserData name can only contain letters and spaces")
    private String fullName;
    private String webPageUrl;
    private String linkedInUrl;
    private String youtubeChannelUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String profession;
    private String aboutMe;
}
