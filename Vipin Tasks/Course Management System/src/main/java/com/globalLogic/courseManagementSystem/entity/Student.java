package com.globalLogic.courseManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;
    private String studentAddress;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courses;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private FinalProject project;
}
