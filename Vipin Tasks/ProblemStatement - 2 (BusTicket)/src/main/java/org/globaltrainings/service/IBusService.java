package org.globaltrainings.service;

import org.globaltrainings.entity.Bus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public interface IBusService {
    Bus addBus(LocalDate dateOfArrival, LocalTime arrivalTime, LocalTime departureTime, String from, String to, double rateOfTicket);
    Bus findBusById(int id);
    List<Bus> findAllBuses();
}
