package org.globaltrainings.dao;

import org.globaltrainings.entity.Ticket;

public interface ITicketDao {
    Ticket generateTicket(Ticket ticket);
    Ticket findTicketById(int id);
}
