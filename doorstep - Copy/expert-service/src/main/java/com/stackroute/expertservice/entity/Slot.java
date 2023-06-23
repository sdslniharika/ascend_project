package com.stackroute.expertservice.entity;

import com.stackroute.expertservice.enums.SlotStatus;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
import java.util.Objects;

public class Slot {

    @Id
    private String slotId;
    private LocalTime slotStartTime;
    private LocalTime slotEndTime;
    private SlotStatus slotStatus;

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public LocalTime getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(LocalTime slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public LocalTime getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(LocalTime slotEndTime) {
        this.slotEndTime = slotEndTime;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(slotStartTime, slot.slotStartTime) && Objects.equals(slotEndTime, slot.slotEndTime) && slotStatus == slot.slotStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotStartTime, slotEndTime, slotStatus);
    }
}
