package com.company.coursya.model;

import com.company.coursya.model.enums.BasicStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document(collection = "courses")
public class Course {

    private String id;
    private String picUrl;
    private String title;
    private String authorId;
    private Double rating;
    private Integer totalRatings;
    private Double price;
    private BasicStatusEnum status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
