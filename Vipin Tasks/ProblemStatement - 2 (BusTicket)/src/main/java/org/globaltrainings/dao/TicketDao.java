package org.globaltrainings.dao;

import org.globaltrainings.datastore.TicketDB;
import org.globaltrainings.entity.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao implements ITicketDao{
    @Override
    public Ticket generateTicket(Ticket ticket) {
        TicketDB.map.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    @Override
    public Ticket findTicketById(int id) {
        if(TicketDB.map.containsKey(id)){
            return TicketDB.map.get(id);
        }
        else{
            throw new RuntimeException("No Ticket found with the given ID");
        }
    }
}
