package org.globaltrainings.entity;

import java.util.Objects;

public class Passenger {
    private String name;
    private String panCard;
    private long contactNumber;
    private String passportNumber;

    public Passenger(){}
    public Passenger(String name, String panCard, long contactNumber, String passportNumber){
        this.name = name;
        this.panCard = panCard;
        this.contactNumber = contactNumber;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return contactNumber == passenger.contactNumber && Objects.equals(name, passenger.name) && Objects.equals(panCard, passenger.panCard) && Objects.equals(passportNumber, passenger.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", panCard='" + panCard + '\'' +
                ", contactNumber=" + contactNumber +
                ", passportNumber='" + passportNumber + '\'' +
                "} \n";
    }
}
