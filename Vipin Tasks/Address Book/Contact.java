package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String name;
    private String organisation;
    private List<PhoneNumber> phoneNumbers;
    private List<Address> addresses;

    public Contact(String name, String organisation) throws Exception {
        // TODO
        if(name.isBlank() || name.matches("[0-9]{1,}") || name.length()>255 || organisation.length()>255){
            throw new Exception();
        }
        this.name=name;
        this.organisation=organisation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        if (this.phoneNumbers == null) {
            this.phoneNumbers = new ArrayList<>();
        }
        this.phoneNumbers.add(phoneNumber);

    }

    public void addAddress(Address address) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.add(address);
    }

}