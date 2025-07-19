package com.company.coursya.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "users")
public class User {

    private String id;
    private String fullName;
    private String authId;
}
