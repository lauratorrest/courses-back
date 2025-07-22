package com.company.coursya.service.impl;

import com.company.coursya.api.dto.user.UpdateProfilePicUrlRequest;
import com.company.coursya.api.dto.user.UpdateUserDetailsRequest;
import com.company.coursya.api.dto.user.UserBasicInfoResponse;
import com.company.coursya.api.dto.user.UserDetailedInfoResponse;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.UserData;
import com.company.coursya.repository.UserRepository;
import com.company.coursya.service.AuthenticationService;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.UserNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public UserServiceImpl(
            @Lazy AuthenticationService authenticationService,
            UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @Override
    public UserData saveUser(String fullName, String authId) {
        return userRepository.save(UserData.builder().fullName(fullName).authId(authId).build());
    }

    @Override
    public UserData findByAuthId(String authId) {
        return userRepository.findByAuthId(authId).orElseThrow(() -> new UserNotFoundException(ExceptionCode.USER_NOT_FOUND));
    }

    @Override
    public UserBasicInfoResponse findByEmail(String email) {
        UserData foundUserData = findUserByEmail(email);
        return UserBasicInfoResponse.builder()
                .fullName(foundUserData.getFullName())
                .profilePictureUrl(foundUserData.getProfilePictureUrl())
                .build();
    }

    private UserData findUserByEmail(String email) {
        AuthenticationData authenticationData = authenticationService.findByEmail(email);
        return findByAuthId(authenticationData.getId());
    }

    @Override
    public UserDetails findUserDetailsByEmail(String email) {
        AuthenticationData authenticationData = authenticationService.findByEmail(email);
        return new User(authenticationData.getEmail(), authenticationData.getPassword(), new ArrayList<>());
    }

    @Override
    public UserDetailedInfoResponse findDetailedUserByEmail(String email) {
        UserData user = findUserByEmail(email);
        return UserDetailedInfoResponse.builder()
                .fullName(user.getFullName())
                .profilePictureUrl(user.getProfilePictureUrl())
                .webPageUrl(user.getWebPageUrl())
                .linkedInUrl(user.getLinkedInUrl())
                .youtubeChannelUrl(user.getYoutubeChannelUrl())
                .facebookUrl(user.getFacebookUrl())
                .instagramUrl(user.getInstagramUrl())
                .profession(user.getProfession())
                .aboutMe(user.getAboutMe())
                .build();
    }

    @Transactional
    @Override
    public UserDetailedInfoResponse updateUserDetails(UpdateUserDetailsRequest request) {
        UserData user = findUserByEmail(request.getEmail());

        user.setFullName(request.getFullName());
        user.setWebPageUrl(request.getWebPageUrl());
        user.setLinkedInUrl(request.getLinkedInUrl());
        user.setYoutubeChannelUrl(request.getYoutubeChannelUrl());
        user.setFacebookUrl(request.getFacebookUrl());
        user.setInstagramUrl(request.getInstagramUrl());
        user.setProfession(request.getProfession());
        user.setAboutMe(request.getAboutMe());
        user.setUpdatedInfoDate(LocalDateTime.now());

        userRepository.save(user);

        return UserDetailedInfoResponse.builder()
                .fullName(user.getFullName())
                .profilePictureUrl(user.getProfilePictureUrl())
                .webPageUrl(user.getWebPageUrl())
                .linkedInUrl(user.getLinkedInUrl())
                .youtubeChannelUrl(user.getYoutubeChannelUrl())
                .facebookUrl(user.getFacebookUrl())
                .instagramUrl(user.getInstagramUrl())
                .profession(user.getProfession())
                .aboutMe(user.getAboutMe())
                .build();
    }

    @Transactional
    @Override
    public void updateUserProfilePicUrl(UpdateProfilePicUrlRequest request) {
        UserData foundUser = findUserByEmail(request.getEmail());
        foundUser.setProfilePictureUrl(request.getProfilePictureUrl());
        foundUser.setUpdateProfilePicDate(LocalDateTime.now());
        userRepository.save(foundUser);
    }

}
