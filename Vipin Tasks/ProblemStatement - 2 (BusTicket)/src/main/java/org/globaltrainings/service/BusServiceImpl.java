package org.globaltrainings.service;

import org.globaltrainings.dao.IBusDao;
import org.globaltrainings.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class BusServiceImpl implements IBusService{
    @Autowired
    private IBusDao busDao;
    private static  int id;
    @Override
    public Bus addBus(LocalDate dateOfArrival, LocalTime arrivalTime, LocalTime departureTime, String from, String to, double rateOfTicket) {
        Bus bus = new Bus(++id, dateOfArrival, arrivalTime, departureTime, from, to, rateOfTicket);
        busDao.addBus(bus);
        return bus;
    }

    @Override
    public Bus findBusById(int id) {
        return busDao.findBusById(id);
    }

    @Override
    public List<Bus> findAllBuses() {
        return busDao.findAllBuses();
    }
}
