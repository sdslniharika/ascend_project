package com.stackroute.expertservice.dto;

import com.stackroute.expertservice.constants.ExpertConstants;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class AddSlot {

    @Schema(example = "13:00:00")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_START_TIME + ExpertConstants.CANT_BE_BLANK)
    private String slotStartTime;
    @Schema(example = "13:30:00")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_END_TIME + ExpertConstants.CANT_BE_BLANK)
    private String slotEndTime;
    @Schema(example = "AVAILABLE")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_STATUS + ExpertConstants.CANT_BE_BLANK)
    private String slotStatus;

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

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddSlot addSlot = (AddSlot) o;
        return Objects.equals(slotStartTime, addSlot.slotStartTime) && Objects.equals(slotEndTime, addSlot.slotEndTime) && Objects.equals(slotStatus, addSlot.slotStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotStartTime, slotEndTime, slotStatus);
    }
}
