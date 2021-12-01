package Server.Model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "seats")
@Entity
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SeatId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public SeatId getId() {
        return id;
    }

    public void setId(SeatId id) {
        this.id = id;
    }

    public int getHallId() {
        return hall.getId();
    }

    public int getRowNum() {
        return id.getRowNum();
    }

    public int getSeatNum() {
        return id.getSeatNum();
    }

    public String getCategoryName() {
        return category.getCategoryName();
    }

    public Seat(SeatId id, Hall hall, Category category) {
        this.id = id;
        this.hall = hall;
        this.category = category;
    }

    public Seat(SeatId id, Hall hall) {
        this.id = id;
        this.hall = hall;
    }

    public Seat() {

    }
}