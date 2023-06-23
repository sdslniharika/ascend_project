package com.globalLogic.courseManagementSystem.dto;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private String courseDuration;
    private Double courseFee;
}
