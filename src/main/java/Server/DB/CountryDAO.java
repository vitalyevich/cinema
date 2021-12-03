package Server.DB;

import Server.Model.CountriesName;
import Server.Model.Country;
import Server.Model.GenresName;

import java.sql.SQLException;
import java.util.List;

public interface CountryDAO {

    public void save(Country country) throws SQLException;
    public void update(Country country);
    public void delete(int id);
    public void truncate();
    public List<Country> view();
    public List<Country> search(int searchId);

    public void addTotal(CountriesName countriesName) throws SQLException;
    public void updateTotal(CountriesName countriesName) throws SQLException;

    public List<CountriesName> searchCountry(int searchId);
}
