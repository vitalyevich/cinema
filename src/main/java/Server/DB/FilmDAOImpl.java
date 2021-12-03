package Server.DB;

import Server.Model.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements FilmDAO{

    @Override
    public void save(Film film) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Film WHERE filmName = :filmName");
        query.setParameter("filmName", film.getFilmName());

        if(query.list().isEmpty()){
            session.save(film);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Film film1 = (Film) session.get(Film.class, film.getId());
        film1.setFilmName(film.getFilmName());
        film1.setReleaseDate(film.getReleaseDate());
        film1.setShowTime(film.getShowTime());
        film1.setFilmDescription(film.getFilmDescription());
        film1.setAgeNum(film.getAgeNum());
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
