package com.company.coursya.api.controller;

import com.company.coursya.api.dto.authentication.RegisterResponse;
import com.company.coursya.api.dto.authentication.RegistrationRequest;
import com.company.coursya.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Tag(name = "Coursya Authentication API", description = "End-points for authentication functions")
@CrossOrigin("*")
@RestController
@RequestMapping("${spring.application.path}")
public class AthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Pre-registration")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> signUpUser(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.savePreRegisterData(
                request.getEmail(), request.getConfirmEmail(), request.getFullName(), request.getPassword()));
    }
}
