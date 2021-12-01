package Server.Model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "genres_name")
@Entity
public class GenresName implements Serializable {

    private static final long serialVersionUID = 1;

    @EmbeddedId
    private GenresNameId id;

    public GenresNameId getId() {
        return id;
    }

    public void setId(GenresNameId id) {
        this.id = id;
    }

    public GenresName() { }

    public GenresName(GenresNameId id) {
        this.id = id;
    }
}