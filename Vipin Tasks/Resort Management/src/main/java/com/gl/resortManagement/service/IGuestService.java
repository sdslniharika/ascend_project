package com.gl.resortManagement.service;
import java.util.Date;
import java.util.List;
import com.gl.resortManagement.constants.*;
import com.gl.resortManagement.dto.AddGuest;
import com.gl.resortManagement.dto.DisplayGuest;
import com.gl.resortManagement.entity.*;

public interface IGuestService {
    DisplayGuest add(AddGuest addGuest);
    DisplayGuest findGuestById(Integer id);
    List<DisplayGuest> findAllGuests();
    List<DisplayGuest> filterGuestsBySpainAndArtsHobby();
    List<DisplayGuest> filterGuestsBySpainHobbyAndAgeMoreThan18();
    List<DisplayGuest> filterGuestsByFranceAndSports();
    List<DisplayGuest> filterGuestsByAgeMoreThan70();
    List<DisplayGuest> filterGuestsByChineseAndRead();
}
