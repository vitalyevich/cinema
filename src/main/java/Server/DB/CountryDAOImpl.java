package Server.DB;

import Server.Model.CountriesName;
import Server.Model.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {

    @Override
    public void save(Country country) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Country WHERE countryName = :countryName");
        query.setParameter("countryName", country.getCountryName());

        if(query.list().isEmpty()){
            session.save(country);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Country country) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Country country1 = (Country) session.get(Country.class, country.getId());
        country1.setCountryName(country.getCountryName());
        session.update(country1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Country country = (Country) session.get(Country.class, id);
        session.delete(country);
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        session.createNativeQuery("delete from countries").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('countries', RESEED, 0)").executeUpdate();
        session.save(new Country("Россия"));
        session.save(new Country("США"));

        transaction.commit();
        session.close();
    }

    private List<Country> countries = new ArrayList<>();
    private List<CountriesName> countriesNames = new ArrayList<>();
    @Override
    public List<Country> view() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        countries = session.createQuery("from Country").list();

        transaction.commit();
        session.close();

        return countries;
    }

    @Override
    public List<Country> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        countries = session.createQuery("from Country where id =" + searchId).list();

        transaction.commit();
        session.close();

        return countries;
    }

    @Override
    public void addTotal(CountriesName countriesName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(countriesName);

        transaction.commit();
        session.close();
    }

    @Override
    public List<CountriesName> searchCountry(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        countriesNames = session.createQuery("from CountriesName where id.filmId =" + searchId).list();

        transaction.commit();
        session.close();

        return countriesNames;
    }
}
