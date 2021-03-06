package coursework.operations;

import coursework.interfaces.GenreDAO;
import coursework.models.Genre;
import coursework.models.GenresName;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {

    @Override
    public void save(Genre genre) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Genre WHERE genreName = :genreName");
        query.setParameter("genreName", genre.getGenreName());

        if(query.list().isEmpty()){
            session.save(genre);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Genre genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Genre genre1 = (Genre) session.get(Genre.class, genre.getId());
        genre1.setGenreName(genre.getGenreName());
        session.update(genre1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Genre genre = (Genre) session.get(Genre.class, id);
        session.delete(genre);
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("delete from genres").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('genres', RESEED, 0)").executeUpdate();
        session.save(new Genre("????????????"));
        session.save(new Genre("??????????"));
        session.save(new Genre("??????????????????????"));
        transaction.commit();
        session.close();
    }

    private List<Genre> genres = new ArrayList<>();
    private List<GenresName> genresNames = new ArrayList<>();
    @Override
    public List<Genre> view() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        genres = session.createQuery("from Genre order by id asc").list();

        transaction.commit();
        session.close();

        return genres;
    }

    @Override
    public List<Genre> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        genres = session.createQuery("from Genre where id =" + searchId).list();

        transaction.commit();
        session.close();

        return genres;
    }

    @Override
    public void addTotal(GenresName genresName) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("FROM GenresName WHERE id.filmId = :filmId AND id.genreId = :genreId");
        query.setParameter("filmId", genresName.getId().getFilmId());
        query.setParameter("genreId", genresName.getId().getGenreId());

        if(query.list().isEmpty()){
            session.save(genresName);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void updateTotal(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createNativeQuery("DELETE FROM genres_name WHERE film_id =:filmId")
                .setParameter("filmId", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<GenresName> searchGenre(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        genresNames = session.createQuery("from GenresName where id.filmId =" + searchId).list();

        transaction.commit();
        session.close();

        return genresNames;
    }
}
