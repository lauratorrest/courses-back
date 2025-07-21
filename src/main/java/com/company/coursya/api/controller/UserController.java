package com.company.coursya.api.controller;

import com.company.coursya.api.dto.user.EmailRequest;
import com.company.coursya.api.dto.user.UserBasicInfoResponse;
import com.company.coursya.api.dto.user.UserDetailedInfoResponse;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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

}
