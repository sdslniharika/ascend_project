package com.stackroute.expertservice.dto;

import com.stackroute.expertservice.constants.ExpertConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddExpertAvailability {

    @Schema(example = "doorstp.service@gmail.com")
    @Length(min = 4, max = 50, message = ExpertConstants.INVALID + ExpertConstants.PARAM_EXPERT_EMAIL + ExpertConstants.LENGTH_BETWEEN)
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_EXPERT_EMAIL + ExpertConstants.CANT_BE_BLANK)
    private String expertEmail;
    @Schema(example = "Plumbing")
    @NotBlank(message = ExpertConstants.INVALID + ExpertConstants.PARAM_SERVICE_NAME + ExpertConstants.CANT_BE_BLANK)
    private String serviceName;
    @Schema(example = "499")
    @Min(value = 0, message = ExpertConstants.INVALID + ExpertConstants.PARAM_PRICE + ExpertConstants.CANT_BE_LESS_THAN + ExpertConstants.NUM_0)
    @NotNull(message = ExpertConstants.INVALID + ExpertConstants.PARAM_PRICE + ExpertConstants.CANT_BE_NULL)
    private Double price;
    @NotEmpty(message = ExpertConstants.INVALID + ExpertConstants.PARAM_AVAILABLE_DATES + ExpertConstants.CANT_BE_EMPTY)
    private Set<@Valid AddAvailableDate> availableDates;

    public String getExpertEmail() {
        return expertEmail;
    }

    public void setExpertEmail(String expertEmail) {
        this.expertEmail = expertEmail;
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<AddAvailableDate> getAvailableDates() {
        return new ArrayList<>(availableDates);
    }

    public void setAvailableDates(Set<AddAvailableDate> availableDates) {
        this.availableDates = availableDates;
    }
}
