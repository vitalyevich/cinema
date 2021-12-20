package coursework.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenresNameId implements Serializable {

    private static final long serialVersionUID = 3041592662606915564L;

    @Column(name = "film_id", nullable = false)
    private Integer filmId;
    @Column(name = "genre_id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GenresNameId entity = (GenresNameId) o;
        return Objects.equals(this.genreId, entity.genreId) &&
                Objects.equals(this.filmId, entity.filmId);
    }

    public GenresNameId(int filmId, int genreId) {
        this.filmId = filmId;
        this.genreId = genreId;
    }

    public GenresNameId() {

    }
}