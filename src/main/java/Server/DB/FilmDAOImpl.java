package Server.DB;

import Server.Model.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements FilmDAO{

    @Override
    public Film findById(int id) {
        return null;
    }

    @Override
    public List<Film> findByFilm(String film) {
        return null;
    }

    @Override
    public void save(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        session.save(film);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Film film1 = (Film) session.get(Film.class, film.getId());
/*        film1.setNameFilm(film.getNameFilm());
        film1.setCountry(film.getCountry());
        film1.setYear(film.getYear());
        film1.setGenre(film.getGenre());
        film1.setTime(film.getTime());
        film1.setPrice(film.getPrice());
        film1.setDescription(film.getDescription());*/
        film1.setRating(film.getRating());
        session.update(film1);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Film WHERE filmName = :name");
        query.setParameter("name", name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Film film = (Film) session.get(Film.class, id);
        session.delete(film);
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM films").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('films', RESEED, 0)").executeUpdate();
        transaction.commit();
        session.close();
    }

    private List<Film> films;
    @Override
    public List<Film> view() {

        this.films = new ArrayList<>();

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        films = session.createQuery("from Film").list();

        transaction.commit();
        session.close();

        return films;
    }

    @Override
    public List<Film> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        films = null;
        films = session.createQuery("from Film where id =" + searchId).list();

        transaction.commit();
        session.close();

        return films;
    }
}
