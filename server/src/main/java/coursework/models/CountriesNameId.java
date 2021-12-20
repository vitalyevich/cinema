package coursework.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CountriesNameId implements Serializable {

    private static final long serialVersionUID = 362964293250326327L;

    @Column(name = "film_id", nullable = false)
    private Integer filmId;
    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, countryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountriesNameId entity = (CountriesNameId) o;
        return Objects.equals(this.filmId, entity.filmId) &&
                Objects.equals(this.countryId, entity.countryId);
    }

    public CountriesNameId() { }

    public CountriesNameId(int filmId, int countryId) {
        this.filmId = filmId;
        this.countryId = countryId;
    }
}