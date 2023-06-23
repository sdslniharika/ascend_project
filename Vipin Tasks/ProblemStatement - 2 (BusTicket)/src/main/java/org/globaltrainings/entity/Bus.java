package org.globaltrainings.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Bus {
    private int id;
    private LocalDate dateOfArrival;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private String from;
    private String to;
    private double rateOfTicket;

    public Bus(){}

    public Bus(int id, LocalDate dateOfArrival, LocalTime arrivalTime, LocalTime departureTime, String from, String to, double rateOfTicket) {
        this.id = id;
        this.dateOfArrival = dateOfArrival;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.from = from;
        this.to = to;
        this.rateOfTicket = rateOfTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getRateOfTicket() {
        return rateOfTicket;
    }

    public void setRateOfTicket(double rateOfTicket) {
        this.rateOfTicket = rateOfTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return id == bus.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", dateOfArrival=" + dateOfArrival +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rateOfTicket=" + rateOfTicket +
                '}';
    }
}
