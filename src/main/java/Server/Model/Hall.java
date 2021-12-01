package Server.Model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "halls")
@Entity
public class Hall implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hall_name", nullable = false, length = 20)
    private String hallName;

    @Column(name = "row_total")
    private Integer rowTotal;

    @Column(name = "seat_total")
    private Integer seatTotal;

    public Integer getSeatTotal() {
        return seatTotal;
    }

    public void setSeatTotal(Integer seatTotal) {
        this.seatTotal = seatTotal;
    }

    public Integer getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(Integer rowTotal) {
        this.rowTotal = rowTotal;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hall() { }

    public Hall(int id, String hallName) {
        this.id = id;
        this.hallName = hallName;
    }

    public Hall(String hallName) {
        this.hallName = hallName;
    }
}