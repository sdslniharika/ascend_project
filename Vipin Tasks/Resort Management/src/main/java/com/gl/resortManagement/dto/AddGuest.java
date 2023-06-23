package com.gl.resortManagement.dto;

import com.gl.resortManagement.constants.Country;
import com.gl.resortManagement.constants.Hobby;
import com.gl.resortManagement.constants.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddGuest {
    private String name;
    private String dob;
    private Country country;
    private Language language;
    private Hobby hobby;
}
