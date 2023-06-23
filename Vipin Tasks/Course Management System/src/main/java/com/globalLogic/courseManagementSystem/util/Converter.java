package com.globalLogic.courseManagementSystem.util;

import com.globalLogic.courseManagementSystem.dto.CourseDTO;
import com.globalLogic.courseManagementSystem.dto.FinalProjectDTO;
import com.globalLogic.courseManagementSystem.dto.StudentDTO;
import com.globalLogic.courseManagementSystem.entity.Course;
import com.globalLogic.courseManagementSystem.entity.FinalProject;
import com.globalLogic.courseManagementSystem.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Converter {
    public static int projectId=0;
    public static int courseId=0;

    public FinalProject toFinalProject(FinalProjectDTO finalProjectDTO){
        FinalProject finalProject = new FinalProject(++projectId, finalProjectDTO.getProjectName(), finalProjectDTO.getDomain());
        return finalProject;
    }

    public Course toCourse(CourseDTO courseDTO){
        Course course = new Course(++courseId, courseDTO.getCourseName(), courseDTO.getCourseDuration(), courseDTO.getCourseFee());
        return course;
    }

    public FinalProjectDTO toFinalProjectDTO(FinalProject finalProject){
        FinalProjectDTO finalProjectDTO = new FinalProjectDTO(finalProject.getProjectId(), finalProject.getProjectName(), finalProject.getDomain());
        return finalProjectDTO;
    }

    public CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO(course.getCourseId(), course.getCourseName(), course.getCourseDuration(), course.getCourseFee());
        return courseDTO;
    }

    public StudentDTO toStudentDTO(Student student){
        StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getStudentName(), student.getStudentAddress(), toCourseDTOs(student.getCourses()), toFinalProjectDTO(student.getProject()));
        return studentDTO;
    }


    public List<Course> toCourseSet(List<CourseDTO> courseDTOS){
        List<Course> courses = new ArrayList<>();
        for (CourseDTO courseDTO : courseDTOS){
            Course course = toCourse(courseDTO);
            courses.add(course);
        }
        return courses;
    }

    public List<CourseDTO> toCourseDTOs(List<Course> courses){
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for(Course course : courses){
            CourseDTO courseDTO = toCourseDTO(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    public List<StudentDTO> toStudentDTOS(List<Student> students){
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Student student: students){
            StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getStudentName(), student.getStudentAddress(), toCourseDTOs(student.getCourses()), toFinalProjectDTO(student.getProject()));

            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

}
