package coursework.models;
import java.io.Serializable;

public class SeatId implements Serializable {
    private static final long serialVersionUID = 3253341428852605988L;

    private Integer rowNum;

    private Integer seatNum;

    private Hall hallId;

    public Hall getHallId() {
        return hallId;
    }

    public SeatId() {
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

    public SeatId(Integer rowNum, Integer seatNum, Hall hallId) {
        this.rowNum = rowNum;
        this.seatNum = seatNum;
        this.hallId = hallId;
    }
}