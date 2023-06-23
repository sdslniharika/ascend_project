package com.gl.resortManagement.util;

import com.gl.resortManagement.constants.Country;
import com.gl.resortManagement.constants.Hobby;
import com.gl.resortManagement.constants.Language;
import com.gl.resortManagement.entity.Guest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class GuestRowMapper implements RowMapper<Guest> {
    @Override
    public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id =rs.getInt("id");
        String name=rs.getString("name");
        LocalDate dob = rs.getDate("dob").toLocalDate();
        System.out.println("After Adding to DB : "+dob);
        String country = rs.getString("country");
        String language = rs.getString("language");
        String hobby = rs.getString("hobby");
        Guest guest=new Guest(id,name,dob, Country.valueOf(country), Language.valueOf(language), Hobby.valueOf(hobby));
        return guest;
    }
}
