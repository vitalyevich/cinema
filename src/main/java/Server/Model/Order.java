package Server.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Table(name = "orders")
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "payment_time", nullable = false)
    private Instant paymentTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "check_num", nullable = false)
    private Long checkNum;

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

    public Order() { }

    public Order(int id, Instant paymentTime, Payment payment, Long checkNum) {
        this.id = id;
        this.paymentTime = paymentTime;
        this.payment = payment;
        this.checkNum = checkNum;
    }

    public Order(Instant paymentTime, Payment payment, Long checkNum) {
        this.paymentTime = paymentTime;
        this.payment = payment;
        this.checkNum = checkNum;
    }
}