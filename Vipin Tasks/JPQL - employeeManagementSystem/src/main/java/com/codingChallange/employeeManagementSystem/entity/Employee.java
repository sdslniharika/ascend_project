package com.codingChallange.employeeManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @Column(unique = true)
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long contactNumber;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Department department;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "employee_to_project",
            joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "projectId"))
    private List<Project> projectList;
}
