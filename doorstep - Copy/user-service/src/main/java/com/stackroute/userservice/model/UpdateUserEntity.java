package com.stackroute.userservice.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateUserEntity {
    @Schema(example = "Kusuma")
    private String firstName;
    @Schema(example = "")
    private String middleName;
    @Schema(example = "Matam")
    private String lastName;
    @Schema(example = "999999123")
    private String mobileNo;
    @Schema(example = "123")
    private String doorNo;
    @Schema(example = "Bank Colony")
    private String street;
    @Schema(example = "Beside Ram Temple")
    private String landmark;
    @Schema(example = "Tirupati")
    private String city;
    @Schema(example = "Chittoor")
    private String dist;
    @Schema(example = "Andra Pradesh")
    private String state;
    @Schema(example = "517507")
    private int pinCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
