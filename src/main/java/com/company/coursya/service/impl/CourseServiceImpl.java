package com.company.coursya.service.impl;

import com.company.coursya.api.dto.course.CourseBasicInfoResponse;
import com.company.coursya.model.Course;
import com.company.coursya.model.UserData;
import com.company.coursya.model.enums.BasicStatusEnum;
import com.company.coursya.repository.CourseRepository;
import com.company.coursya.service.CourseService;
import com.company.coursya.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final UserService userService;
    private final CourseRepository courseRepository;

    @Override
    public List<CourseBasicInfoResponse> findAllActive() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::mapCourse).collect(Collectors.toList());
    }

    private CourseBasicInfoResponse mapCourse(Course course){
        UserData author = userService.findByAuthId(course.getAuthorId());
        return CourseBasicInfoResponse.builder()
                .id(course.getId())
                .picUrl(course.getPicUrl())
                .title(course.getTitle())
                .authorName(author.getFullName())
                .rating(course.getRating())
                .totalRatings(course.getTotalRatings())
                .price(course.getPrice())
                .build();
    }

    @Override
    public void saveNewCourse() {
        for (int i = 0; i < 5; i++) {
            Course course = Course.builder()
                    .id("123")
                    .picUrl("https://vilmanunez.com/wp-content/uploads/2016/03/herramientas-y-recursos-para-crear-curso-online.png")
                    .title("Cómo Aprender " + (i + 1))
                    .authorId("687c3766fa6f54ba5bd91fda")
                    .rating(4.5)
                    .totalRatings(6000)
                    .price(15.99)
                    .status(BasicStatusEnum.ACTIVE)
                    .build();
            courseRepository.save(course);
        }

    }

}
