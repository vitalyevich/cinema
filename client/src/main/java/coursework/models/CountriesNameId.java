package coursework.models;

import java.io.Serializable;
import java.util.Objects;

public class CountriesNameId implements Serializable {

    private static final long serialVersionUID = 362964293250326327L;

    private Integer filmId;

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

    public CountriesNameId() { }

    public CountriesNameId(int filmId, int countryId) {
        this.filmId = filmId;
        this.countryId = countryId;
    }
}