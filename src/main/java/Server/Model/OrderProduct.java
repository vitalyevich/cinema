package Server.Model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "order_products")
@Entity
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderProductId id;

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public OrderProduct() { }

    public OrderProduct(OrderProductId id) {
        this.id = id;
    }
}