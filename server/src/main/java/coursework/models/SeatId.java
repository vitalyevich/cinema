package coursework.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatId implements Serializable {
    private static final long serialVersionUID = 3253341428852605988L;
    @Column(name = "row_num", nullable = false)
    private Integer rowNum;
    @Column(name = "seat_num", nullable = false)
    private Integer seatNum;
    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hallId;

    public Hall getHallId() {
        return hallId;
    }

    public void setHallId(Hall hallId) {
        this.hallId = hallId;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNum, hallId, rowNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeatId entity = (SeatId) o;
        return Objects.equals(this.seatNum, entity.seatNum) &&
                Objects.equals(this.hallId, entity.hallId) &&
                Objects.equals(this.rowNum, entity.rowNum);
    }
}