package com.company.coursya.api.controller;

import com.company.coursya.api.dto.course.CourseBasicInfoResponse;
import com.company.coursya.service.CourseService;
import com.company.coursya.shared.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@Tag(name = "Coursya Courses API", description = "End-points for courses functions")
@CrossOrigin("*")
@RestController
@RequestMapping(Constants.API_PATH)
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Find Courses")
    @PostMapping(Constants.FIND_COURSES)
    public ResponseEntity<List<CourseBasicInfoResponse>> findAllCourses(){
        return ResponseEntity.ok(courseService.findAllActive());
    }
}
