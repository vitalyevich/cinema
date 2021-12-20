package coursework.models;

import java.io.Serializable;


public class Check implements Serializable {

    private static final long serialVersionUID = 1L;

    private CheckId id;

    public CheckId getId() {
        return id;
    }

    public void setId(CheckId id) {
        this.id = id;
    }

    public Check() { }

    public Check(CheckId id) {
        this.id = id;
    }

    public Integer getIdOrder() {
        return id.getOrderId();
    }
    public String getIdStaff() {
        return id.getStaffId().getLastName();
    }
}