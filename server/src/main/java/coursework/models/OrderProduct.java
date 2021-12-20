package coursework.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "order_products")
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private OrderProductId id;

    @Column(name = "amount_total", nullable = false)
    private Integer amountTotal;

    public Integer getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Integer amountTotal) {
        this.amountTotal = amountTotal;
    }

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }
}