package com.gl.resortManagement.entity;

import com.gl.resortManagement.constants.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {
    private Integer id;
    private String name;
    private LocalDate dob;
    private Country country;
    private Language language;
    private Hobby hobby;

}
