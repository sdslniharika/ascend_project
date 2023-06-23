package com.codingChallange.employeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Project")
@Table(name = "Project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private String projectName;
    private String projectLocation;

    @ManyToMany(mappedBy = "projectList", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Employee> employeeList;
}
