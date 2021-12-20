package coursework.operations;

import coursework.interfaces.HallDAO;
import coursework.models.Hall;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDAOImpl implements HallDAO {

    @Override
    public void save(Hall hall) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Hall WHERE hallName = :hallName");
        query.setParameter("hallName", hall.getHallName());

        if(query.list().isEmpty()){

            session.save(hall);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Hall hall) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Hall hall1 = (Hall) session.get(Hall.class, hall.getId());
        hall1.setHallName(hall.getHallName());
        hall1.setRowTotal(hall.getRowTotal());
        hall1.setSeatTotal(hall.getSeatTotal());
        session.update(hall1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Hall hall = (Hall) session.get(Hall.class, id);
        session.delete(hall);
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("delete from halls").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('halls', RESEED, 0)").executeUpdate();
        transaction.commit();
        session.close();
    }

    private List<Hall> halls = new ArrayList<>();
    @Override
    public List<Hall> view() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        halls = session.createQuery("from Hall").list();

        transaction.commit();
        session.close();

        return halls;
    }

    @Override
    public List<Hall> view(int hallId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        halls = session.createQuery("from Hall where id =:hallId")
                .setParameter("hallId", hallId).list();

        transaction.commit();
        session.close();

        return halls;
    }

    @Override
    public List<Hall> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        halls = session.createQuery("from Hall where id =" + searchId).list();

        transaction.commit();
        session.close();

        return halls;
    }
}
