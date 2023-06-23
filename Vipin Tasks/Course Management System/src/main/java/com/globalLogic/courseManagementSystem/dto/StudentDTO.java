package com.globalLogic.courseManagementSystem.dto;

import com.globalLogic.courseManagementSystem.entity.Course;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentDTO {
    private Integer studentId;
    private String studentName;
    private String studentAddress;
    private List<CourseDTO> courses;
    private FinalProjectDTO project;
}
