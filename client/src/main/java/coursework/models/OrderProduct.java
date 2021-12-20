package coursework.models;

import java.awt.*;
import java.io.Serializable;
import java.time.Instant;

public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private OrderProductId id;

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

    public OrderProduct(OrderProductId id, Integer amountTotal) {
        this.id = id;
        this.amountTotal = amountTotal;
    }

    public OrderProduct() {
    }

    public Long getCheck() {
        return id.getOrderId().getCheckNum();
    }

    public Instant getDate() {
        return id.getOrderId().getPaymentTime();
    }

    public String getPayment() {
        return  id.getOrderId().getPayment().getPaymentName();
    }

    public Long getMoney() {
        return id.getOrderId().getPrice();
    }

}