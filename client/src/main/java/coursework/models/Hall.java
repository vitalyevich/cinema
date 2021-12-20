package coursework.models;

import java.io.Serializable;

public class Hall implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String hallName;

    private Integer rowTotal;

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

    public Hall(Integer id, String hallName, Integer rowTotal, Integer seatTotal) {
        this.id = id;
        this.hallName = hallName;
        this.rowTotal = rowTotal;
        this.seatTotal = seatTotal;
    }

    public Hall(String hallName, Integer rowTotal, Integer seatTotal) {
        this.hallName = hallName;
        this.rowTotal = rowTotal;
        this.seatTotal = seatTotal;
    }
}