package coursework.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CheckId implements Serializable {

    private static final long serialVersionUID = -4930969983296668364L;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staffId;
    @Column(name = "order_id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CheckId entity = (CheckId) o;
        return Objects.equals(this.orderId, entity.orderId) &&
                Objects.equals(this.staffId, entity.staffId);
    }

    public CheckId() { }

    public CheckId(Staff staffId, int orderId) {
        this.staffId = staffId;
        this.orderId = orderId;
    }
}