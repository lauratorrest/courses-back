package com.company.coursya.api.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserBasicInfoResponse {

    private String fullName;
}
