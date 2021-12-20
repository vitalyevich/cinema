package coursework.models;

import java.io.Serializable;
import java.util.Objects;


public class OrderProductId implements Serializable {
    private static final long serialVersionUID = 6987853322199812625L;

    private Order orderId;

    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public OrderProductId(Order orderId, Integer productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderProductId() {
    }
}