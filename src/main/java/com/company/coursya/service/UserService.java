package com.company.coursya.service;

import com.company.coursya.api.dto.user.UserInfoResponse;
import com.company.coursya.model.User;

public interface UserService {

    User saveUser(String fullName, String authId);

    User findByAuthId(String id);

    UserInfoResponse findByEmail(String email);
}
