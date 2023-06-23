package com.globalLogic.courseManagementSystem.service;

import com.globalLogic.courseManagementSystem.dto.StudentDTO;
import com.globalLogic.courseManagementSystem.entity.Course;
import com.globalLogic.courseManagementSystem.entity.FinalProject;
import com.globalLogic.courseManagementSystem.entity.Student;
import com.globalLogic.courseManagementSystem.repository.StudentRepository;
import com.globalLogic.courseManagementSystem.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Converter converter;
    private static int studentId=0;
    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        FinalProject finalProject = converter.toFinalProject(studentDTO.getProject());
        List<Course> courses = converter.toCourseSet(studentDTO.getCourses());
        Student student = new Student(++studentId, studentDTO.getStudentName(), studentDTO.getStudentAddress(), courses, finalProject);
        student = studentRepository.save(student);
        return converter.toStudentDTO(student);
    }

    @Override
    public List<StudentDTO> findAllStudent() {
       List<Student> students = studentRepository.findAll();
       if(students.isEmpty()){
           throw new RuntimeException("No Students are available on DB");
       }
        return converter.toStudentDTOS(students);
    }

    @Override
    public StudentDTO findStudentById(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()){
            throw new RuntimeException("No Students are available on DB with Id : "+id);
        }
        Student student = optionalStudent.get();
        return converter.toStudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(studentDTO.getStudentId());
        if(optionalStudent.isEmpty()){
            throw new RuntimeException("No Students are available on DB with Id : "+studentDTO.getStudentId());
        }
        Student student = optionalStudent.get();

        student.setStudentName(studentDTO.getStudentName());
        student = studentRepository.save(student);
        return converter.toStudentDTO(student);
    }

    @Override
    public String deleteSTudent(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()){
            throw new RuntimeException("No Students are available on DB with Id : "+id);
        }
        Student student = optionalStudent.get();
        System.out.println(student.getStudentName()+" after find by id in delete block");
        studentRepository.deleteById(id);
        return "Student with ID : "+id+" and Name : "+student.getStudentName()+" has been deleted successfully";
    }
}
