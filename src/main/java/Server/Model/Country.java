package Server.Model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "countries", indexes = {
        @Index(name = "UQ__countrie__F7018894DBD4B289", columnList = "country_name", unique = true)
})
@Entity
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "country_name", nullable = false, length = 20)
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