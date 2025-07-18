package com.company.coursya.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document(collection = "authentication")
public class AuthenticationData {

    private String id;
    private String email;
    private Boolean active;
    private LocalDateTime createdDate;
}
