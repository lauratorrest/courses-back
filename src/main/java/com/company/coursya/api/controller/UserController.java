package com.company.coursya.api.controller;

import com.company.coursya.api.dto.user.EmailRequest;
import com.company.coursya.api.dto.user.UserInfoResponse;
import com.company.coursya.service.UserService;
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
@RequestMapping("${spring.application.path}")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Find User Info By Email")
    @PostMapping("/user/find-by-email")
    public ResponseEntity<UserInfoResponse> findByEmail(@RequestBody EmailRequest request) {
        return ResponseEntity.ok(userService.findByEmail(request.getEmail()));
    }
}
