package com.codingChallange.employeeManagementSystem.dto;

import com.codingChallange.employeeManagementSystem.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentRequest {
    private String deptName;
    private List<EmpDTO> employees;
}
