package com.company.coursya.api.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDetailedInfoResponse {

    private String fullName;
    private String webPageUrl;
    private String linkedInUrl;
    private String youtubeChannelUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String profilePictureUrl;
    private String profession;
    private String aboutMe;
}
