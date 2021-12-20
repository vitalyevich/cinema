package coursework.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Instant paymentTime;

    private Payment payment;

    private Long checkNum;

    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(Long checkNum) {
        this.checkNum = checkNum;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Instant getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Instant paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order(Instant paymentTime, Payment payment, Long checkNum, Long price) {
        this.paymentTime = paymentTime;
        this.payment = payment;
        this.checkNum = checkNum;
        this.price = price;
    }

    public Order(Integer id, Instant paymentTime, Payment payment, Long checkNum, Long price) {
        this.id = id;
        this.paymentTime = paymentTime;
        this.payment = payment;
        this.checkNum = checkNum;
        this.price = price;
    }

    public Order() {
    }

    public String getPaymentName() {
        return payment.getPaymentName();
    }
}