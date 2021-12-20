package coursework.models;

import java.io.Serializable;

public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

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