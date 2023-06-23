package org.globaltrainings.entity;

import java.util.List;
import java.util.Objects;

public class Ticket {
    private int ticketId;
    private Bus bus;
    private int noOfSeats;
    private List<Passenger> passengerList;
    private double totalAmount;

    public Ticket(int ticketId, Bus bus, int noOfSeats, List<Passenger> passengerList, double totalAmount) {
        this.ticketId = ticketId;
        this.bus = bus;
        this.noOfSeats = noOfSeats;
        this.passengerList = passengerList;
        this.totalAmount = totalAmount;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", bus=" + bus +
                ", noOfSeats=" + noOfSeats +
                ", passengerList=" + passengerList +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
