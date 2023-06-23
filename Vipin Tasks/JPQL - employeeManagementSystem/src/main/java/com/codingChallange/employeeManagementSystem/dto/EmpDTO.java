package com.codingChallange.employeeManagementSystem.dto;

import com.codingChallange.employeeManagementSystem.entity.Department;
import com.codingChallange.employeeManagementSystem.entity.Project;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpDTO {
    private Integer empId;
    private String firstName;
    private String LastName;
    private LocalDate birthDate;
    private Long contactNumber;
}
