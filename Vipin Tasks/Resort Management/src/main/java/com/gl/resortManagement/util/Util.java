package com.gl.resortManagement.util;

import com.gl.resortManagement.dto.AddGuest;
import com.gl.resortManagement.dto.DisplayGuest;
import com.gl.resortManagement.entity.Guest;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Util {
    public Guest toGuest(AddGuest addGuest){
        String[] dateArray = addGuest.getDob().split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);

        String dateText = year+"-0"+month+"-"+day;
        LocalDate localDate = LocalDate.parse(dateText);

        Guest guest = new Guest();
        guest.setName(addGuest.getName());
        guest.setDob(localDate);
        guest.setCountry(addGuest.getCountry());
        guest.setLanguage(addGuest.getLanguage());
        guest.setHobby(addGuest.getHobby());

        return guest;
    }

    public DisplayGuest toDisplayGuest(Guest guest){
        DisplayGuest displayGuest = new DisplayGuest();
        displayGuest.setId(guest.getId());
        displayGuest.setName(guest.getName());
        displayGuest.setDob(guest.getDob());
        displayGuest.setCountry(guest.getCountry());
        displayGuest.setLanguage(guest.getLanguage());
        displayGuest.setHobby(guest.getHobby());

        return displayGuest;
    }

    public List<DisplayGuest> toDisplayGuestList(List<Guest> guestList){
        List<DisplayGuest> displayGuestList = new ArrayList<>();
        for(Guest guest: guestList){
            DisplayGuest displayGuest = new DisplayGuest();
            displayGuest.setId(guest.getId());
            displayGuest.setName(guest.getName());
            displayGuest.setDob(guest.getDob());
            displayGuest.setCountry(guest.getCountry());
            displayGuest.setLanguage(guest.getLanguage());
            displayGuest.setHobby(guest.getHobby());

            displayGuestList.add(displayGuest);
        }
        return displayGuestList;
    }
}
