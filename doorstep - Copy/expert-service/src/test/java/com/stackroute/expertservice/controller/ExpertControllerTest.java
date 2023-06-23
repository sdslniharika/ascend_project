package com.stackroute.expertservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.expertservice.dto.*;
import com.stackroute.expertservice.entity.AvailableDate;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.Slot;
import com.stackroute.expertservice.enums.SlotStatus;
import com.stackroute.expertservice.service.IExpertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpertController.class)
class ExpertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    IExpertService service;

    private List<ExpertAvailability> expertAvailabilityList;

    private ExpertAvailability expertAvailability;

    private Slot slot;

    @BeforeEach
    void setUp() {
        expertAvailabilityList = new ArrayList<>();
        expertAvailability = new ExpertAvailability();

        Set<Slot> slots = new HashSet<>();
        slot = new Slot();
        slot.setSlotId("1");
        slot.setSlotStartTime(LocalTime.now());
        slot.setSlotEndTime(LocalTime.now().plusMinutes(30));
        slots.add(slot);

        List<AvailableDate> availableDates = new ArrayList<>();
        AvailableDate availableDate = new AvailableDate();
        availableDate.setSlotDate(LocalDate.now());
        availableDate.setSlots(slots);
        availableDates.add(availableDate);

        expertAvailability.setExpertEmail("expert@email.com");
        expertAvailability.setServiceName("Plumbing");
        expertAvailability.setPrice(10000.0);
        expertAvailability.setAvailableDates(availableDates);
        expertAvailabilityList.add(expertAvailability);
    }

    @Test
    void addAvailabilityTest() throws Exception {
        Set<AddSlot> addSlots = new HashSet<>();
        AddSlot addSlot = new AddSlot();
        addSlot.setSlotStartTime("12:12:12");
        addSlot.setSlotEndTime("12:32:12");
        addSlot.setSlotStatus(SlotStatus.AVAILABLE.name());
        addSlots.add(addSlot);

        Set<AddAvailableDate> addAvailableDates = new HashSet<>();
        AddAvailableDate addAvailableDate = new AddAvailableDate();
        addAvailableDate.setSlotDate("2023-12-31");
        addAvailableDate.setSlots(addSlots);
        addAvailableDates.add(addAvailableDate);

        AddExpertAvailability addExpertAvailability = new AddExpertAvailability();
        addExpertAvailability.setExpertEmail("expert@email.com");
        addExpertAvailability.setServiceName("Plumbing");
        addExpertAvailability.setPrice(1000.0);
        addExpertAvailability.setAvailableDates(addAvailableDates);

        Mockito.when(service.addAvailability(Mockito.any())).thenReturn(expertAvailability);

        mockMvc
                .perform(post("/api/v1/availability/saveAvailability").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(addExpertAvailability).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isCreated());
    }

    @Test
    void addSlotTest() throws Exception {
        Set<AddSlot> addSlots = new HashSet<>();
        AddSlot addSlot = new AddSlot();
        addSlot.setSlotStartTime("12:12:12");
        addSlot.setSlotEndTime("12:32:12");
        addSlot.setSlotStatus(SlotStatus.AVAILABLE.name());
        addSlots.add(addSlot);

        AddSlotToExpertAvailability addSlotToExpertAvailability = new AddSlotToExpertAvailability();
        addSlotToExpertAvailability.setExpertEmail("expert@email.com");
        addSlotToExpertAvailability.setServiceName("Plumbing");
        addSlotToExpertAvailability.setSlotDate(LocalDate.now().toString());
        addSlotToExpertAvailability.setSlots(addSlots);

        Mockito.when(service.addSlots(Mockito.any())).thenReturn(expertAvailability);

        mockMvc
                .perform(put("/api/v1/availability/addSlots").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(addSlotToExpertAvailability).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isAccepted());
    }

    @Test
    void addAvailableDateTest() throws Exception {
        Set<AddSlot> addSlots = new HashSet<>();
        AddSlot addSlot = new AddSlot();
        addSlot.setSlotStartTime("12:12:12");
        addSlot.setSlotEndTime("12:32:12");
        addSlot.setSlotStatus(SlotStatus.AVAILABLE.name());
        addSlots.add(addSlot);

        AddAvailableDate addAvailableDate = new AddAvailableDate();
        addAvailableDate.setSlotDate("2023-12-31");
        addAvailableDate.setSlots(addSlots);

        AddAvailableDateToExpertAvailability addAvailableDateToExpertAvailability = new AddAvailableDateToExpertAvailability();
        addAvailableDateToExpertAvailability.setExpertEmail("expert@email.com");
        addAvailableDateToExpertAvailability.setServiceName("Plumbing");
        addAvailableDateToExpertAvailability.setAvailableDate(addAvailableDate);

        Mockito.when(service.addAvailableDate(Mockito.any())).thenReturn(expertAvailability);

        mockMvc
                .perform(put("/api/v1/availability/addAvailableDate").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(addAvailableDateToExpertAvailability).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isAccepted());
    }

    @Test
    void getAvailableExpertsTest() throws Exception {
        Mockito.when(service.getAllAvailableExperts(Optional.of(true))).thenReturn(expertAvailabilityList);
        mockMvc.perform(get("/api/v1/availability/allAvailableExperts"))
                .andExpect(status().isOk());
    }

    @Test
    void getAvailableExpertsByServiceIdTest() throws Exception {
        Mockito.when(service.getAvailableExperts(Mockito.any(), Mockito.any())).thenReturn(expertAvailabilityList);
        mockMvc.perform(get("/api/v1/availability/availableExperts?serviceName=Plumbing"))
                .andExpect(status().isOk());
    }

    @Test
    void updateSlot() throws Exception {
        UpdateSlot updateSlot = new UpdateSlot();
        updateSlot.setSlotId("1");
        updateSlot.setSlotStartTime("12:12:12");
        updateSlot.setSlotEndTime("12:32:12");

        SlotDetails slotDetails = new SlotDetails();
        slotDetails.setSlotId("1");

        Mockito.when(service.updateSlot(Mockito.any())).thenReturn(slotDetails);

        mockMvc
                .perform(put("/api/v1/availability/updateSlot").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateSlot).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isAccepted());
    }

    @Test
    void updateSlotStatus() throws Exception {
        UpdateSlotStatus updateSlotStatus = new UpdateSlotStatus();
        updateSlotStatus.setSlotId("1");
        updateSlotStatus.setSlotStatus(SlotStatus.AVAILABLE.name());

        SlotDetails slotDetails = new SlotDetails();
        slotDetails.setSlotId("1");

        Mockito.when(service.updateSlot(Mockito.any())).thenReturn(slotDetails);

        mockMvc
                .perform(put("/api/v1/availability/updateSlotStatus").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateSlotStatus).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isAccepted());
    }

    @Test
    void deleteSlotTest() throws Exception {
        Mockito.when(service.deleteSlot(Mockito.any())).thenReturn("1");
        mockMvc.perform(delete("/api/v1/availability/slot/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    void deleteServiceTest() throws Exception {
        Mockito.when(service.deleteService(Mockito.any(), Mockito.any())).thenReturn("expert@email.com");
        mockMvc.perform(delete("/api/v1/availability/deleteAvailability?expertEmail=expert@email.com&serviceName=Plumbing"))
                .andExpect(status().isAccepted());
    }
}
