package org.globaltrainings.service;

import org.globaltrainings.entity.Bus;
import org.globaltrainings.entity.Passenger;
import org.globaltrainings.entity.Ticket;

import java.util.List;

public interface ITicketService {
    Ticket registerTicket(Bus bus, int noOfSeats, List<Passenger> passengerList, double totalAmount);
    Ticket findTicketById(int id);
}
