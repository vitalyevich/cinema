package Server.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TicketsPriceId implements Serializable {

    private static final long serialVersionUID = 7164507045982169866L;

    @Column(name = "seance_id", nullable = false)
    private Integer seanceId;
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TicketsPriceId entity = (TicketsPriceId) o;
        return Objects.equals(this.seanceId, entity.seanceId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    public TicketsPriceId() { }

    public TicketsPriceId(int seanceId, int categoryId) {
        this.seanceId = seanceId;
        this.categoryId = categoryId;
    }
}