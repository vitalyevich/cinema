package Server.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderTicketId implements Serializable {

    private static final long serialVersionUID = -2946152777258013566L;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Column(name = "ticket_id", nullable = false)
    private Integer ticketId;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, ticketId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderTicketId entity = (OrderTicketId) o;
        return Objects.equals(this.orderId, entity.orderId) &&
                Objects.equals(this.ticketId, entity.ticketId);
    }

    public OrderTicketId() { }

    public OrderTicketId(int orderId, int ticketId) {
        this.orderId = orderId;
        this.ticketId = ticketId;
    }
}