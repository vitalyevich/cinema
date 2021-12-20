package coursework.interfaces;

import coursework.models.Ticket;
import coursework.models.TicketsPrice;

import java.util.List;

public interface TicketDAO {

    public List<TicketsPrice> viewPrice();
    public List<Ticket> viewTicket();
}
