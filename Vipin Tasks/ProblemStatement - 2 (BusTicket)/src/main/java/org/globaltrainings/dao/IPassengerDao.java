package org.globaltrainings.dao;

import org.globaltrainings.entity.Passenger;

import java.util.List;

public interface IPassengerDao {
    Passenger addPassenger(Passenger passenger);
    List<Passenger> findAllPassengers();
}
