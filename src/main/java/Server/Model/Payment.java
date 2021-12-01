package Server.Model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "payments", indexes = {
        @Index(name = "UQ__payments__3CDE62D64220FD04", columnList = "payment_name", unique = true)
})
@Entity
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "payment_name", length = 20)
    private String paymentName;

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Payment() { }

    public Payment(int id, String paymentName) {
        this.id = id;
        this.paymentName = paymentName;
    }

    public Payment(String paymentName) {
        this.paymentName = paymentName;
    }
}