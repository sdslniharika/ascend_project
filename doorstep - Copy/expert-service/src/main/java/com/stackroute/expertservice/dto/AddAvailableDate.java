package com.stackroute.expertservice.dto;

import com.stackroute.expertservice.constants.ExpertConstants;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class AddAvailableDate {

    @Schema(example = "2023-01-10")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_DATE + ExpertConstants.CANT_BE_BLANK)
    private String slotDate;
    @NotEmpty(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOTS + ExpertConstants.CANT_BE_EMPTY)
    private Set<@Valid AddSlot> slots;

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public Set<AddSlot> getSlots() {
        return slots;
    }

    public void setSlots(Set<AddSlot> slots) {
        this.slots = slots;
    }
}
