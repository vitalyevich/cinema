package coursework.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "order_tickets")
@Entity
public class OrderTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
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