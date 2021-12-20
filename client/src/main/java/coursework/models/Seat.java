package coursework.models;
import java.io.Serializable;

public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;
    private SeatId id;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SeatId getId() {
        return id;
    }

    public void setId(SeatId id) {
        this.id = id;
    }

    public Seat(SeatId id, Category category) {
        this.id = id;
        this.category = category;
    }

    public Seat() {
    }

    public Seat(SeatId id) {
        this.id = id;
    }

    public Integer getHallId() {
        return id.getHallId().getId();
    }

    public Integer getRowNum() {
        return id.getRowNum();
    }

    public Integer getSeatNum() {
        return id.getSeatNum();
    }

    public String getCategoryName() {
        return category.getCategoryName();
    }
}