package com.company.coursya.service;

import com.company.coursya.api.dto.course.CourseBasicInfoResponse;

import java.util.List;

public interface CourseService {

    List<CourseBasicInfoResponse> findAllActive();
}
