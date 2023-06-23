package com.gl.resortManagement.dao;

import com.gl.resortManagement.entity.Guest;
import com.gl.resortManagement.exceptions.InvalidInputException;
import com.gl.resortManagement.util.GuestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class GuestDao implements IGuestDao{
    private static int generatedId;
    @Autowired
    private JdbcTemplate template;

    private final String insertQuery="insert into guests(id,name,dob,country,language,hobby) values(?,?,?,?,?,?)";
    private final String fetchGuestQuery="select * from guests where id=?";
    private final String fetchAllGuestsQuery="select * from guests";
    private final String fetchAllGuests18Query="select * from guests WHERE EXTRACT(YEAR FROM dob) > (2023-18)";
    private final String fetchAllGuests70Query="select * from guests WHERE EXTRACT(YEAR FROM dob) > (2023-70)";

    @Override
    public Guest add(Guest guest) {
        int newId = ++generatedId;
        int rows = template.update(insertQuery, newId, guest.getName(), guest.getDob(), guest.getCountry().toString(), guest.getLanguage().toString(), guest.getHobby().toString());
        if(rows<1){
            throw new InvalidInputException("Unable to Guest to Database");
        }
        else{
            guest.setId(newId);
            return guest;
        }
    }

    @Override
    public Optional<Guest> findById(int id) {
        Guest guest = template.queryForObject(fetchGuestQuery, new GuestRowMapper(), id);
        if (guest == null) {
            Optional<Guest> optional = Optional.empty();
            return optional;
        }
        return Optional.of(guest);
    }

    @Override
    public List<Guest> findAllGuests() {
        return template.query(fetchAllGuestsQuery, new GuestRowMapper());
    }



}
