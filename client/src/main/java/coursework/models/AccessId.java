package coursework.models;

import java.io.Serializable;
import java.util.Objects;

public class AccessId implements Serializable {

    private static final long serialVersionUID = -7868755310450963023L;

    private Staff staffId;

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
    
    public AccessId() { }

    public AccessId(Staff staffId, Department departmentId) {
        this.staffId = staffId;
        this.departmentId = departmentId;
    }
}