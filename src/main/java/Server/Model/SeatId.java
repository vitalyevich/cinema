package Server.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatId implements Serializable {

    private static final long serialVersionUID = 8716514345692929472L;

    @Column(name = "row_num", nullable = false)
    private Integer rowNum;
    @Column(name = "seat_num", nullable = false)
    private Integer seatNum;

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
        return Objects.hash(seatNum, rowNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeatId entity = (SeatId) o;
        return Objects.equals(this.seatNum, entity.seatNum) &&
                Objects.equals(this.rowNum, entity.rowNum);
    }

    public SeatId() { }

    public SeatId(int rowNum, int seatNum) {
        this.rowNum = rowNum;
        this.seatNum = seatNum;
    }
}