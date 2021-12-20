package coursework.models;

import java.io.Serializable;

public class OrderTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    private OrderTicketId id;

    public OrderTicketId getId() {
        return id;
    }

    public void setId(OrderTicketId id) {
        this.id = id;
    }

    public OrderTicket() { }

    public OrderTicket(OrderTicketId id) {
        this.id = id;
    }
}