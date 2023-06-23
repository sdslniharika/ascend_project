package com.gl.resortManagement.service;
import com.gl.resortManagement.constants.Country;
import com.gl.resortManagement.constants.Hobby;
import com.gl.resortManagement.constants.Language;
import com.gl.resortManagement.dao.GuestDao;
import com.gl.resortManagement.dto.AddGuest;
import com.gl.resortManagement.dto.DisplayGuest;
import com.gl.resortManagement.entity.Guest;
import com.gl.resortManagement.service.GuestService;
import com.gl.resortManagement.service.IGuestService;
import com.gl.resortManagement.util.Util;
import com.gl.resortManagement.validations.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceTestCases {
    @Mock
    IGuestService iService;
    @Mock
    Validator validator;
    @Mock
    JdbcTemplate template = new JdbcTemplate();
    @Mock
    GuestDao guestDao = new GuestDao();
    @InjectMocks
    GuestService service;
    @Spy
    Util util;
    private Guest guest;
    private AddGuest addGuest;
    private List<Guest> guestList;

    @BeforeEach
    void setup() {
        guest = new Guest();
        addGuest = new AddGuest();
        guestList = new ArrayList<>();

        String dateText = "1965-08-21";
        LocalDate localDate = LocalDate.parse(dateText);

        guest.setId(1);
        guest.setName("Vinod");
        guest.setDob(localDate);
        guest.setCountry(Country.USA);
        guest.setLanguage(Language.English);
        guest.setHobby(Hobby.Drink);
        guestList.add(guest);

        addGuest.setName("Vinod");
        addGuest.setDob("1976-09-21");
        addGuest.setCountry(Country.USA);
        addGuest.setLanguage(Language.English);
        addGuest.setHobby(Hobby.Drink);
    }

    @Test
    void addCustomerTest() {
        Mockito.when(guestDao.add(Mockito.any())).thenReturn(guest);
        DisplayGuest displayGuest = service.add(addGuest);
        Assertions.assertEquals(addGuest.getName(), displayGuest.getName());
    }

    @Test
    void findAllGuests() {
        Mockito.when(guestDao.findAllGuests()).thenReturn(guestList);
        List<DisplayGuest> displayGuestList = service.findAllGuests();
        Assertions.assertEquals(guestList.size(), displayGuestList.size());
    }

}
