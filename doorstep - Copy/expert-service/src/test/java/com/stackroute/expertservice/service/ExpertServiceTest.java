package com.stackroute.expertservice.service;

import com.stackroute.expertservice.dto.*;
import com.stackroute.expertservice.entity.AvailableDate;
import com.stackroute.expertservice.entity.ExpertAvailability;
import com.stackroute.expertservice.entity.Slot;
import com.stackroute.expertservice.enums.SlotStatus;
import com.stackroute.expertservice.exception.ExpertAvailabilityNotFoundException;
import com.stackroute.expertservice.exception.InvalidArgumentException;
import com.stackroute.expertservice.exception.SlotNotFoundException;
import com.stackroute.expertservice.mapper.ExpertMapper;
import com.stackroute.expertservice.repository.ExpertRepository;
import com.stackroute.expertservice.utils.ExpertUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpertServiceTest {

    @InjectMocks
    ExpertService service;

    @Mock
    ExpertRepository repository;

    @Spy
    ExpertMapper expertMapper;

    private List<ExpertAvailability> expertAvailabilityList;

    private ExpertAvailability expertAvailability;


    @BeforeEach
    void setUp() {
        ExpertUtils.enableTestingMode();
        expertAvailabilityList = new ArrayList<>();
        expertAvailability = new ExpertAvailability();

        Set<Slot> slots = new HashSet<>();
        Slot slot = new Slot();
        slot.setSlotId("1");
        slot.setSlotStartTime(LocalTime.now());
        slot.setSlotEndTime(LocalTime.now().plusMinutes(30));
        slot.setSlotStatus(SlotStatus.AVAILABLE);
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

    @AfterEach
    void reset() {
        ExpertUtils.disableTestingMode();
    }

    @Test
    void addAvailabilityTest() throws InvalidArgumentException {
        AddExpertAvailability addExpertAvailability = new AddExpertAvailability();
        addExpertAvailability.setExpertEmail("expert@email.com");
        addExpertAvailability.setServiceName("Plumbing");
        addExpertAvailability.setPrice(10000.0);
        addExpertAvailability.setAvailableDates(new HashSet<>());

        when(repository.save(Mockito.any())).thenReturn(expertAvailability);
        ExpertAvailability expertAvailability = service.addAvailability(addExpertAvailability);
        assertEquals(addExpertAvailability.getExpertEmail(), expertAvailability.getExpertEmail());
        assertFalse(expertMapper.toString().isBlank());
    }

    @Test
    void addSlotTest() throws InvalidArgumentException, ExpertAvailabilityNotFoundException {
        Set<AddSlot> addSlots = new HashSet<>();
        AddSlot addSlot = new AddSlot();
        addSlot.setSlotStartTime("12:12:12");
        addSlot.setSlotEndTime("12:32:12");
        addSlot.setSlotStatus(SlotStatus.AVAILABLE.name());
        addSlots.add(addSlot);

        AddSlotToExpertAvailability addSlotToExpertAvailability = new AddSlotToExpertAvailability();
        addSlotToExpertAvailability.setExpertEmail("expert@email.com");
        addSlotToExpertAvailability.setServiceName("Plumbing");
        addSlotToExpertAvailability.setSlotDate(LocalDate.now().plusDays(1).toString());
        addSlotToExpertAvailability.setSlots(addSlots);

        when(repository.findByExpertEmailAndServiceNameAndAvailableDates_SlotDateGreaterThanEqual(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(expertAvailabilityList);
        when(repository.save(Mockito.any())).thenReturn(expertAvailability);
        ExpertAvailability expertAvailability = service.addSlots(addSlotToExpertAvailability);
        assertEquals(addSlotToExpertAvailability.getExpertEmail(), expertAvailability.getExpertEmail());
        assertFalse(expertMapper.toString().isBlank());
    }

    @Test
    void addAvailableDateTest() throws InvalidArgumentException, ExpertAvailabilityNotFoundException {
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

        when(repository.findByExpertEmailAndServiceNameAndAvailableDates_SlotDateGreaterThanEqual(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(expertAvailabilityList);
        when(repository.save(Mockito.any())).thenReturn(expertAvailability);
        ExpertAvailability expertAvailability = service.addAvailableDate(addAvailableDateToExpertAvailability);
        assertEquals(addAvailableDateToExpertAvailability.getExpertEmail(), expertAvailability.getExpertEmail());
        assertFalse(expertMapper.toString().isBlank());
    }

    @Test
    void getAllAvailableExpertsTest() {
        when(repository.findByAvailableDates_Slots_SlotStatusAndAvailableDates_SlotDateGreaterThanEqual(Mockito.any(), Mockito.any())).thenReturn(expertAvailabilityList);
        assertEquals(1, service.getAllAvailableExperts(Optional.of(true)).size());
    }

    @Test
    void getAvailableExpertsByServiceIdTest() {
        when(repository.findByExpertEmailAndServiceNameAndAvailableDates_SlotDateGreaterThanEqual(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(expertAvailabilityList);
        assertEquals(1, service.getAvailableExperts("Plumbing", Optional.of("expert@email.com")).size());
    }

    @Test
    void updateSlotTest() throws InvalidArgumentException, SlotNotFoundException {
        UpdateSlot updateSlot = new UpdateSlot();
        updateSlot.setSlotId("1");
        LocalTime time = LocalTime.now();
        updateSlot.setSlotStartTime(time.plusMinutes(10).toString());
        updateSlot.setSlotEndTime(time.plusMinutes(30).toString());
        when(repository.findAll()).thenReturn(expertAvailabilityList);
        when(repository.save(Mockito.any())).thenReturn(expertAvailability);
        assertNotNull(service.updateSlot(updateSlot).getSlotId());
    }

    @Test
    void updateSlotStatusTest() throws InvalidArgumentException, SlotNotFoundException {
        UpdateSlotStatus updateSlotStatus = new UpdateSlotStatus();
        updateSlotStatus.setSlotId("1");
        updateSlotStatus.setSlotStatus(SlotStatus.AVAILABLE.name());
        when(repository.findAll()).thenReturn(expertAvailabilityList);
        when(repository.save(Mockito.any())).thenReturn(expertAvailability);
        assertNotNull(service.updateSlotStatus(updateSlotStatus, Optional.of("1")));
    }

    @Test
    void deleteSlotTest() throws SlotNotFoundException {
        when(repository.findAll()).thenReturn(expertAvailabilityList);
        when(repository.save(Mockito.any())).thenReturn(expertAvailability);
        assertEquals("1", service.deleteSlot("1"));
    }

    @Test
    void deleteServiceTest() throws SlotNotFoundException, ExpertAvailabilityNotFoundException {
        when(repository.deleteByExpertEmailAndServiceName(Mockito.any(), Mockito.any())).thenReturn("1");
        assertEquals("Plumbing", service.deleteService("expert@email.com", Optional.of("Plumbing")));
    }

}
