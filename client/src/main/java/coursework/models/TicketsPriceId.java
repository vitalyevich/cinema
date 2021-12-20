package coursework.models;


import java.io.Serializable;
import java.util.Objects;

public class TicketsPriceId implements Serializable {

    private static final long serialVersionUID = 7164507045982169866L;

    private Integer seanceId;

    private Category categoryId;

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(Integer seanceId) {
        this.seanceId = seanceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seanceId, categoryId);
    }


    public TicketsPriceId() { }

    public TicketsPriceId(int seanceId, Category categoryId) {
        this.seanceId = seanceId;
        this.categoryId = categoryId;
    }
}