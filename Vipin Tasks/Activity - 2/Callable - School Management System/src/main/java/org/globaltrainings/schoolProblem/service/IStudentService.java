package org.globaltrainings.schoolProblem.service;

import org.globaltrainings.schoolProblem.dto.StudentDTO;
import org.globaltrainings.schoolProblem.entity.Student;

import java.util.List;

public interface IStudentService {
    int addStudent(StudentDTO studentDTO);
    List<Student> getAllStudents();
    Student findStudentByID(int studentId);
    String updateStudentName(int studentid, Student student);
    String deleteStudent(int studentId);
}
