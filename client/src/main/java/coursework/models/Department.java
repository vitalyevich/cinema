package coursework.models;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String position;

    private Long salary;

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department(String position, Long salary) {
        this.position = position;
        this.salary = salary;
    }

    public Department(int id) {
        this.id = id;
    }

    public Department() { }
}