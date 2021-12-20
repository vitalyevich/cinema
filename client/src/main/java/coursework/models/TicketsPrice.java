package coursework.models;

import java.io.Serializable;

public class TicketsPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    private TicketsPriceId id;

    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public TicketsPriceId getId() {
        return id;
    }

    public void setId(TicketsPriceId id) {
        this.id = id;
    }

    public TicketsPrice() { }

    public TicketsPrice(TicketsPriceId id, Long price) {
        this.id = id;
        this.price = price;
    }

    public Integer getSeanceId() {
        return id.getSeanceId();
    }

    public String getCategoryName() {
        return id.getCategoryId().getCategoryName();
    }
}