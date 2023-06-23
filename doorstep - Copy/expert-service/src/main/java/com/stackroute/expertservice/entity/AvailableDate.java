package com.stackroute.expertservice.entity;

import java.time.LocalDate;
import java.util.Set;

public class AvailableDate {

    private LocalDate slotDate;
    private Set<Slot> slots;


    public LocalDate getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(LocalDate slotDate) {
        this.slotDate = slotDate;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
    }
}
