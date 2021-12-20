package coursework.models;

import java.io.Serializable;
import java.util.Objects;

public class OrderTicketId implements Serializable {

    private static final long serialVersionUID = -2946152777258013566L;

    private Integer orderId;

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

    public OrderTicketId() { }

    public OrderTicketId(int orderId, int ticketId) {
        this.orderId = orderId;
        this.ticketId = ticketId;
    }
}