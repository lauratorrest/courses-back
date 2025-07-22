package com.company.coursya.service;

import com.company.coursya.api.dto.user.UpdateProfilePicUrlRequest;
import com.company.coursya.api.dto.user.UpdateUserDetailsRequest;
import com.company.coursya.api.dto.user.UserBasicInfoResponse;
import com.company.coursya.api.dto.user.UserDetailedInfoResponse;
import com.company.coursya.model.UserData;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserData saveUser(String fullName, String authId);

    UserData findByAuthId(String id);

    UserBasicInfoResponse findByEmail(String email);

    UserDetails findUserDetailsByEmail(String email);

    UserDetailedInfoResponse findDetailedUserByEmail(String email);

    UserDetailedInfoResponse updateUserDetails(UpdateUserDetailsRequest request);

    void updateUserProfilePicUrl(UpdateProfilePicUrlRequest request);
}
