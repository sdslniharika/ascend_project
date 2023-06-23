package com.stackroute.emailservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slot {

    private String slotId;
    @Schema(example = "31/12/2022")
    String slotDate;
    @Schema(example = "13:00:00")
    @NotBlank(message = "SlotStartId can not be blank")
    private String slotStartTime;
    @Schema(example = "13:30:00")
    @NotBlank(message = "SlotEndTime can not be blank")
    private String slotEndTime;
    @Schema(example = "AVAILABLE")
    @NotBlank(message = "SlotStatus can not be blank")
    private String slotStatus;

}