package coursework.models;

import java.io.Serializable;

public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    private AccessId id;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccessId getId() {
        return id;
    }

    public void setId(AccessId id) {
        this.id = id;
    }

    public int getCount() {
        return id.getStaffId().getId();
    }

    public String getLastName() {
        return id.getStaffId().getLastName();
    }

    public String getFirstName() {
        return id.getStaffId().getFirstName();
    }

    public String getMiddleName() {
        return id.getStaffId().getMiddleName();
    }

    public Character getGender() {
        return id.getStaffId().getGender();
    }

    public String getPhone() {
        return id.getStaffId().getPhone();
    }

    public String getPosition() {
        return id.getDepartmentId().getPosition();
    }

    public Long getSalary() {
        return id.getDepartmentId().getSalary();
    }

    public Access() { }

    public Access(AccessId id, String  password) {
        this.id = id;
        this.password = password;
    }

    public Access(AccessId id) {
        this.id = id;
        this.password = password;
    }

    public Access(String  password) {
        this.password = password;
    }
}
