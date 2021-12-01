package Server.Model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "checks")
@Entity
public class Check implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CheckId id;

    public CheckId getId() {
        return id;
    }

    public void setId(CheckId id) {
        this.id = id;
    }

    public Check() { }

    public Check(CheckId id) {
        this.id = id;
    }
}