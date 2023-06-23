package com.stackroute.expertservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "expert_availability")
public class ExpertAvailability {

    @Id
    private String expertEmail;
    private String serviceName;
    private Double price;

    private List<AvailableDate> availableDates;

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

    public List<AvailableDate> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<AvailableDate> availableDates) {
        this.availableDates = availableDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpertAvailability that = (ExpertAvailability) o;
        return Objects.equals(expertEmail, that.expertEmail) && Objects.equals(serviceName, that.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expertEmail, serviceName);
    }
}
