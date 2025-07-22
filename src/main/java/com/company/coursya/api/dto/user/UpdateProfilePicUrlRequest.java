package com.company.coursya.api.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateProfilePicUrlRequest {

    private String email;
    private String profilePictureUrl;
}
