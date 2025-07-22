package com.company.coursya.api.controller;

import com.company.coursya.api.dto.user.EmailRequest;
import com.company.coursya.api.dto.user.UpdateProfilePicUrlRequest;
import com.company.coursya.api.dto.user.UpdateUserDetailsRequest;
import com.company.coursya.api.dto.user.UserBasicInfoResponse;
import com.company.coursya.api.dto.user.UserDetailedInfoResponse;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Tag(name = "Coursya Users API", description = "End-points for user functions")
@CrossOrigin("*")
@RestController
@RequestMapping(Constants.API_PATH)
public class UserController {

    private final UserService userService;

    @Operation(summary = "Find UserData Info By Email")
    @PostMapping(Constants.FIND_USER_BY_EMAIL)
    public ResponseEntity<UserBasicInfoResponse> findByEmail(@RequestBody EmailRequest request) {
        return ResponseEntity.ok(userService.findByEmail(request.getEmail()));
    }

    @Operation(summary = "Find Detailed User Data info By Email")
    @PostMapping(Constants.FIND_DETAILED_USER_BY_EMAIL)
    public ResponseEntity<UserDetailedInfoResponse> findDetailsByEmail(@RequestBody EmailRequest request) {
        return ResponseEntity.ok(userService.findDetailedUserByEmail(request.getEmail()));
    }

    @Operation(summary = "Updating Detailed data info")
    @PutMapping(Constants.UPDATE_USER_DETAILS)
    public ResponseEntity<UserDetailedInfoResponse> updateDetails(@Valid @RequestBody UpdateUserDetailsRequest request) {
        return ResponseEntity.ok(userService.updateUserDetails(request));
    }

    @Operation(summary = "Updating user profile picture url")
    @PutMapping(Constants.UPDATE_PROFILE_PIC_URL)
    public void updateProfilePicUrl(@RequestBody UpdateProfilePicUrlRequest request) {
        userService.updateUserProfilePicUrl(request);
    }

}
