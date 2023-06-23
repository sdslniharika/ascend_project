package com.globalLogic.swimmers.entity;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Swimmer")
public class Swimmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double age;

    public Swimmer(String name, double age){
        this.name=name;
        this.age=age;
    }
}
