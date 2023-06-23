package com.codingChallange.employeeManagementSystem.dto;

import com.codingChallange.employeeManagementSystem.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentResponce {
    private Integer deptId;
    private String deptName;
    private List<Employee> employeeList;
}
