package org.globaltrainings.schoolProblem.frontEnd;

import org.globaltrainings.schoolProblem.dto.StudentDTO;
import org.globaltrainings.schoolProblem.entity.Student;
import org.globaltrainings.schoolProblem.service.StudentServiceImpl;

import java.util.List;

public class SchoolRegister {
    public void run(){
        StudentServiceImpl service = new StudentServiceImpl();

        StudentDTO st1 = new StudentDTO(1001, "Rahul", "Banglore", "rahul@gmail.com");
        StudentDTO st2 = new StudentDTO(1002, "Rohit", "Mumbai", "rohit@gmail.com");
        StudentDTO st3 = new StudentDTO(1003, "Warner", "Sydney", "warner@gmail.com");
        StudentDTO st4 = new StudentDTO(1004, "Smith", "Sydney", "smith@gmail.com");
        StudentDTO st5 = new StudentDTO(1005, "Stokes", "London", "stokes@gmail.com");
        StudentDTO st6 = new StudentDTO(1006, "Virat", "Delhi", "virat@gmail.com");


        System.out.println("----------------------------------Add Students --------------------------------------------");
        int s1 = service.addStudent(st1);
        System.out.println("The Student has been added succesfully with the id : "+s1);
        int s2 = service.addStudent(st2);
        System.out.println("The Student has been added succesfully with the id : "+s2);
        int s3 = service.addStudent(st3);
        System.out.println("The Student has been added succesfully with the id : "+s3);
        int s4 = service.addStudent(st4);
        System.out.println("The Student has been added succesfully with the id : "+s4);
        int s5 = service.addStudent(st5);
        System.out.println("The Student has been added succesfully with the id : "+s5);
        int s6 = service.addStudent(st6);
        System.out.println("The Student has been added succesfully with the id : "+s6);
        System.out.println();

        System.out.println("----------------------------------Get Students by Id-------------------------------------");
        Student student = service.findStudentByID(1003);
        System.out.println("The student details with id : "+1003+" are as follows -> "+student);
        System.out.println();

        System.out.println("----------------------------------Get All Students   -------------------------------------");
        List<Student> studentList = service.getAllStudents();
        displayListOfStudents(studentList);
        System.out.println();

        System.out.println("----------------------------------Update Student by ID-------------------------------------");
        String update = service.updateStudentName(1006, new Student(1006, "Virat Kohli", "Delhi", "virat@gmail.com"));
        System.out.println(update);
        System.out.println();

        System.out.println("----------------------------------Get All Students after Update   -------------------------------------");
        List<Student> studentList1 = service.getAllStudents();
        displayListOfStudents(studentList1);
        System.out.println();

        System.out.println("----------------------------------Delete Movies by ID-------------------------------------");
        String delete = service.deleteStudent(1005);
        System.out.println(delete);

        System.out.println("----------------------------------Get All Students after Delete   -------------------------------------");
        List<Student> studentList2 = service.getAllStudents();
        displayListOfStudents(studentList2);
        System.out.println();


    }

    public void displayListOfStudents(List<Student> studentList){
        System.out.println();
        System.out.println("The List of all the students of the school are  : \n");
        for(Student s: studentList){
            System.out.println("    "+s);
        }
    }



}
