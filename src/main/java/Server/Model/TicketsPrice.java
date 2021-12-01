package Server.Model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "tickets_price")
@Entity
public class TicketsPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TicketsPriceId id;

    @Column(name = "price", precision = 10, scale = 4)
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
}