package coursework.models;

import java.io.Serializable;

public class CountriesName implements Serializable {

    private static final long serialVersionUID = 1;

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