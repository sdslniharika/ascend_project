package com.globalLogic.courseManagementSystem.controller;

import com.globalLogic.courseManagementSystem.dto.StudentDTO;
import com.globalLogic.courseManagementSystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms")
public class CorserManagementAPI {
    @Autowired
    private IStudentService service;

    @PostMapping("/addStudent")
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO){
        return service.addStudent(studentDTO);
    }

    @PutMapping("/updateStudent")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO){
        return service.updateStudent(studentDTO);
    }

    @GetMapping("/findAllStudents")
    public List<StudentDTO> findAllStudents(){
        return service.findAllStudent();
    }

    @GetMapping("/student-by-id/{id}")
    public StudentDTO findStudentById(@PathVariable Integer id){
        return service.findStudentById(id);
    }

    @DeleteMapping("/delete-student-by-id/{id}")
    public String deleteStudentById(@PathVariable Integer id){
        return service.deleteSTudent(id);
    }
}
