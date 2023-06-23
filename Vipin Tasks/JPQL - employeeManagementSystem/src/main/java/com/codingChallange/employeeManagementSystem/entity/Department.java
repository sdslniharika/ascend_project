package com.codingChallange.employeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;
    @Column(unique = true)
    private String deptName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    @JsonIgnore
    private List<Employee> employeeList;
}
