package com.example.demo;

public class PhoneNumber {
    private String label;
    private String phoneNumber;

    public PhoneNumber(String label, String phoneNumber) throws Exception {
        // TODO
        super();
        if(phoneNumber.matches("[0-9]{1,}[A-Za-z]{1,}") || label.isBlank()|| label.length()>25){
            throw new Exception();
        }
        this.label=label;
        this.phoneNumber=phoneNumber;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
