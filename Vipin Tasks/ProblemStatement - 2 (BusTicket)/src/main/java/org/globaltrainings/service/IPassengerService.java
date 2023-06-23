package org.globaltrainings.service;


import org.globaltrainings.entity.Passenger;

import java.util.List;

public interface IPassengerService {
    Passenger registerPassenger(String name, String panCard, long contactNumber, String passportNumber);
    List<Passenger> findAllPassengers();
}
