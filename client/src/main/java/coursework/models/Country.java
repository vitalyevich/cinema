package coursework.models;

import java.io.Serializable;

public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country() { }

    public Country(int id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }
}