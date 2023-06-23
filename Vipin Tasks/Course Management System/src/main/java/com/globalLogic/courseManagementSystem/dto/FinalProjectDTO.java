package com.globalLogic.courseManagementSystem.dto;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FinalProjectDTO {
    private Integer projectId;
    private String projectName;
    private String domain;
}
