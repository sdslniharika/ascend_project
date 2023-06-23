package org.globaltrainings.service;


import org.globaltrainings.dao.IPassengerDao;
import org.globaltrainings.entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerServiceImpl implements IPassengerService {
    @Autowired
    private IPassengerDao passengerDao;
    @Override
    public Passenger registerPassenger(String name, String panCard, long contactNumber, String passportNumber) {
        Passenger passenger = new Passenger(name, panCard, contactNumber, passportNumber);
        passengerDao.addPassenger(passenger);
        return passenger;
    }

    @Override
    public List<Passenger> findAllPassengers() {
        return passengerDao.findAllPassengers();
    }
}
