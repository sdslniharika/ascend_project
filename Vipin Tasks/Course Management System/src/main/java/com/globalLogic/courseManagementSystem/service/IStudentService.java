package com.globalLogic.courseManagementSystem.service;

import com.globalLogic.courseManagementSystem.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    StudentDTO addStudent(StudentDTO studentDTO);
    List<StudentDTO> findAllStudent();
    StudentDTO findStudentById(Integer id);
    StudentDTO updateStudent(StudentDTO studentDTO);
    String deleteSTudent(Integer id);
}
