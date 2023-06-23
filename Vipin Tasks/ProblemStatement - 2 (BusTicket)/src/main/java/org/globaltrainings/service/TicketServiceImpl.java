package org.globaltrainings.service;

import org.globaltrainings.dao.ITicketDao;
import org.globaltrainings.entity.Bus;
import org.globaltrainings.entity.Passenger;
import org.globaltrainings.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService{
    @Autowired
    private ITicketDao ticketDao;
    private static int id;
    @Override
    public Ticket registerTicket(Bus bus, int noOfSeats, List<Passenger> passengerList, double totalAmount) {
        Ticket ticket = new Ticket(++id, bus, noOfSeats, passengerList, totalAmount);
        ticketDao.generateTicket(ticket);
        return ticket;
    }

    @Override
    public Ticket findTicketById(int id) {
        return ticketDao.findTicketById(id);
    }
}
