package com.company.coursya.api.dto.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseBasicInfoResponse {

    private String id;
    private String picUrl;
    private String title;
    private String authorName;
    private Double rating;
    private Integer totalRatings;
    private Double price;
}
