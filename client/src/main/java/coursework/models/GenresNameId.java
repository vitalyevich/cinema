package coursework.models;

import java.io.Serializable;
import java.util.Objects;

public class GenresNameId implements Serializable {

    private static final long serialVersionUID = 3041592662606915564L;

    private Integer filmId;

    private Integer genreId;

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, filmId);
    }

    public GenresNameId(int filmId, int genreId) {
        this.filmId = filmId;
        this.genreId = genreId;
    }

    public GenresNameId() {

    }
}