package com.company.coursya.service;

import com.company.coursya.api.dto.user.UserInfoResponse;
import com.company.coursya.model.UserData;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserData saveUser(String fullName, String authId);

    UserData findByAuthId(String id);

    UserInfoResponse findByEmail(String email);

    UserDetails findUserDetailsByEmail(String email);
}
