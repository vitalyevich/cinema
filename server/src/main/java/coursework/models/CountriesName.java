package coursework.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "countries_name")
@Entity
public class CountriesName implements Serializable {

    private static final long serialVersionUID = 1;

    @EmbeddedId
    private CountriesNameId id;

    public CountriesNameId getId() {
        return id;
    }

    public void setId(CountriesNameId id) {
        this.id = id;
    }

    public CountriesName() { }

    public CountriesName(CountriesNameId id) {
        this.id = id;
    }
}