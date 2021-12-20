package coursework.models;

import java.io.Serializable;

public class GenresName implements Serializable {

    private static final long serialVersionUID = 1;

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