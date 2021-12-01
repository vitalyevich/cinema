package Server.DB;

import Server.Model.Film;
import Server.Model.Seance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAOImpl implements SeanceDAO {

    @Override
    public void save(Seance seance) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Seance WHERE showTime = :showTime"); // чтобы разница была
        query.setParameter("showTime", seance.getShowTime());

        if(query.list().isEmpty()){
            session.save(seance);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Seance seance) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Seance seance1 = (Seance) session.get(Seance.class, seance.getId());
        seance1.setShowDate(seance.getShowDate());
        seance1.setShowTime(seance.getShowTime());
        seance1.setHall(seance.getHall());
        seance1.setFilm(seance.getFilm());
        session.update(seance1);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Seance seance = (Seance) session.get(Seance.class, id);
        session.delete(seance);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteByName(Film id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Seance WHERE film =: id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteByDate(LocalDate date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Seance WHERE showDate =: date");
        query.setParameter("date", date);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("delete from seances ").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('seances', RESEED, 0)").executeUpdate();
        transaction.commit();
        session.close();
    }

    private List<Seance> seances = new ArrayList<>();
    @Override
    public List<Seance> view() {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        seances = session.createQuery("from Seance").list();

        transaction.commit();
        session.close();

        return seances;
    }

    @Override
    public List<Seance> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        seances = session.createQuery("from Seance where id =" + searchId).list();

        transaction.commit();
        session.close();

        return seances;
    }
}
