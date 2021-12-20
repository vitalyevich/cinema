package coursework.models;

import java.io.Serializable;
import java.util.Objects;

public class CheckId implements Serializable {

    private static final long serialVersionUID = -4930969983296668364L;

    private Staff staffId;

    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, staffId);
    }

    public CheckId() { }

    public CheckId(Staff staffId, int orderId) {
        this.staffId = staffId;
        this.orderId = orderId;
    }
}