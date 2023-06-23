package com.gl.resortManagement.dto;

import com.gl.resortManagement.constants.Country;
import com.gl.resortManagement.constants.Hobby;
import com.gl.resortManagement.constants.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisplayGuest {
    private Integer id;
    private String name;
    private LocalDate dob;
    private Country country;
    private Language language;
    private Hobby hobby;
}
