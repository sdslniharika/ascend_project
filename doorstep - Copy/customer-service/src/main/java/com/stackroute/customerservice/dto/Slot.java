package com.stackroute.customerservice.dto;

import com.stackroute.customerservice.constants.SlotStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public class Slot {
    @Schema(example = "abcd1234abcd1234abcd1234")
    String slotId;
    @Schema(example = "31/12/2022")
    String slotDate;

    @Schema(example = "13:00:00")
    String slotStartTime;
    @Schema(example = "13:30:00")
    String slotEndTime;
    @Schema(example = "NOT_AVAILABLE")
    SlotStatus slotStatus;

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public String getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(String slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public String getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(String slotEndTime) {
        this.slotEndTime = slotEndTime;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }
}
