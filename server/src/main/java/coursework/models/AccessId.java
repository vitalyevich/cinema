package coursework.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccessId implements Serializable {

    private static final long serialVersionUID = -7868755310450963023L;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staffId;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department departmentId;

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, staffId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccessId entity = (AccessId) o;
        return Objects.equals(this.departmentId, entity.departmentId) &&
                Objects.equals(this.staffId, entity.staffId);
    }

    public AccessId() { }

    public AccessId(Staff staffId, Department departmentId) {
        this.staffId = staffId;
        this.departmentId = departmentId;
    }
}