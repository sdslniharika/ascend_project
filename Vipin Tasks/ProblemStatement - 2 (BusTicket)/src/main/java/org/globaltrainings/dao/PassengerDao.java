package org.globaltrainings.dao;

import org.globaltrainings.datastore.PassengerDB;
import org.globaltrainings.entity.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassengerDao implements IPassengerDao{
    @Override
    public Passenger addPassenger(Passenger passenger) {
        PassengerDB.map.put(passenger.getName(), passenger);
        return passenger;
    }

    @Override
    public List<Passenger> findAllPassengers() {
        List<Passenger> passengerList = PassengerDB.map.values().stream().collect(Collectors.toList());
        if(passengerList.isEmpty()){
            throw new RuntimeException("Nop Passesngers Registerd");
        }
        else{
            return passengerList;
        }
    }
}
