package org.globaltrainings.service;

import org.globaltrainings.datastore.PassengerDB;
import org.globaltrainings.entity.Bus;
import org.globaltrainings.entity.Passenger;
import org.globaltrainings.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookBus {
    @Autowired
    IBusService busService;
    @Autowired
    IPassengerService passengerService;
    @Autowired
    ITicketService ticketService;

    public Ticket bookBus(String to, String from, String[] passengerNames){
        List<Bus> busList = busService.findAllBuses();
        List<Passenger> passengerList = new ArrayList<>();
        Bus bus = new Bus();

        //Bus Object
        busList = busList.stream().filter(bus1 -> bus1.getTo().equalsIgnoreCase(to) && bus1.getFrom().equalsIgnoreCase(from))
                .collect(Collectors.toList());
        if(busList.isEmpty()){
            throw new RuntimeException("No Busses available for the mentioned route");
        }
        else{
            Optional<Bus> optionalBus = busList.stream().findAny();
            bus = optionalBus.get();
        }

        //For Passenger List
        for (int i=0; i<passengerNames.length; i++){
            if(PassengerDB.map.containsKey(passengerNames[i])){
                passengerList.add(PassengerDB.map.get(passengerNames[i]));
            }
            else{
                throw new RuntimeException("The Passenger is not a Registerd.");
            }
        }
        //For Total amount
        double totalFare = bus.getRateOfTicket() * passengerList.size();
        //For Ticket
        return ticketService.registerTicket(bus, passengerList.size(), passengerList, totalFare);

    }
}
