package com.codingChallange.employeeManagementSystem.dto;

import com.codingChallange.employeeManagementSystem.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectRequest {
    private String projectName;
    private String projectLocation;
    private List<EmpDTO> employees;
}
