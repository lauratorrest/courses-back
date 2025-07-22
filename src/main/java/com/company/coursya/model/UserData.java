package com.company.coursya.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document(collection = "users")
public class UserData {

    private String id;
    private String fullName;
    private String authId;
    private String webPageUrl;
    private String linkedInUrl;
    private String youtubeChannelUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String profilePictureUrl;
    private String profession;
    private String aboutMe;
    private LocalDateTime updatedInfoDate;
    private LocalDateTime updateProfilePicDate;
}
