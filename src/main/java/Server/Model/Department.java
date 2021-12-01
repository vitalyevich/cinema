package Server.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "departments", indexes = {
        @Index(name = "UQ__departme__75FE9D9914FEB630", columnList = "position", unique = true)
})
@Entity
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "\"position\"", nullable = false, length = 20)
    private String position;

    @Column(name = "salary", nullable = false, precision = 10, scale = 4)
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