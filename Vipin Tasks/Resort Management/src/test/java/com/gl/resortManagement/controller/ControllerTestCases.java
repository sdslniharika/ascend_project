package com.gl.resortManagement.controller;
import com.gl.resortManagement.constants.Country;
import com.gl.resortManagement.constants.Hobby;
import com.gl.resortManagement.constants.Language;
import com.gl.resortManagement.controller.ResortApi;
import com.gl.resortManagement.dto.AddGuest;
import com.gl.resortManagement.dto.DisplayGuest;
import com.gl.resortManagement.entity.Guest;
import com.gl.resortManagement.service.IGuestService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebMvcTest(ResortApi.class)
public class ControllerTestCases {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    IGuestService service;
    private AddGuest addGuest;
    private DisplayGuest displayGuest;
    private Guest guest;
    List<DisplayGuest> guestList;

    @BeforeEach
    void setup() {
        addGuest = new AddGuest();
        displayGuest = new DisplayGuest();
        guest = new Guest();
        guestList = new ArrayList<>();

        String dateText = "1965-08-21";
        LocalDate localDate = LocalDate.parse(dateText);

        displayGuest.setId(1);
        displayGuest.setName("Vinod");
        displayGuest.setDob(localDate);
        displayGuest.setCountry(Country.USA);
        displayGuest.setLanguage(Language.English);
        displayGuest.setHobby(Hobby.Drink);
    }

    public String getObjectInJson() {
        return "{\n" +
                "  \"name\": \"Vinod\",\n" +
                "  \"dob\": \"1976-09-21\",\n" +
                "  \"country\": \"USA\",\n" +
                "  \"language\": \"English\",\n" +
                "  \"hobby\": \"Drink\"\n" +
                "}";
    }

    @Test
    void addCustomer() throws Exception {
        Mockito.when(service.add(addGuest)).thenReturn(displayGuest);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/resort/addGuest")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(getObjectInJson());
        mockMvc.perform(builder).andExpect(status().isCreated());

    }

    @Test
    void getCustomerById() throws Exception {
        Mockito.when(service.findGuestById(Mockito.any())).thenReturn(displayGuest);
        mockMvc.perform(MockMvcRequestBuilders.get("/resort/findByID/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        Mockito.when(service.findAllGuests()).thenReturn(guestList);
        mockMvc.perform(MockMvcRequestBuilders.get("/resort/findAllGuests"))
                .andExpect(status().isOk());
    }

    @Test
    void getFilterSpain1() throws Exception {
        Mockito.when(service.filterGuestsBySpainAndArtsHobby()).thenReturn(guestList);
        mockMvc.perform(MockMvcRequestBuilders.get("/resort/filterBySpainAndArtsHobby"))
                .andExpect(status().isOk());
    }

    @Test
    void getFilterSpain2() throws Exception {
        Mockito.when(service.filterGuestsBySpainHobbyAndAgeMoreThan18()).thenReturn(guestList);
        mockMvc.perform(MockMvcRequestBuilders.get("/resort/filterBySpainHobbyAndAgeMoreThan18"))
                .andExpect(status().isOk());
    }

    @Test
    void getFilterFrance() throws Exception {
        Mockito.when(service.filterGuestsByFranceAndSports()).thenReturn(guestList);
        mockMvc.perform(MockMvcRequestBuilders.get("/resort/filterByFranceAndSports"))
                .andExpect(status().isOk());
    }

    @Test
    void getFilterByAge() throws Exception {
        Mockito.when(service.filterGuestsByAgeMoreThan70()).thenReturn(guestList);
        mockMvc.perform(MockMvcRequestBuilders.get("/resort/filterByAgeMoreThan70"))
                .andExpect(status().isOk());
    }

    @Test
    void getFilterChina() throws Exception {
        Mockito.when(service.filterGuestsByChineseAndRead()).thenReturn(guestList);
        mockMvc.perform(MockMvcRequestBuilders.get("/resort/filterByChineseAndRead"))
                .andExpect(status().isOk());
    }



}
