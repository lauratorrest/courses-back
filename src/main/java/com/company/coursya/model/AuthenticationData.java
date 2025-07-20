package com.company.coursya.model;

import com.company.coursya.model.enums.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@Document(collection = "authentication")
public class AuthenticationData {

    private String id;
    private String email;
    private String password;
    private UserRoleEnum userRole;
    private Boolean active;
    private LocalDateTime createdDate;
}
