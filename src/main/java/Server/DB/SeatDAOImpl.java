package Server.DB;

import Server.Model.Seat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAOImpl implements SeatDAO {

    @Override
    public void save(Seat seat) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query query = session.createQuery("from Seat where hall.id =:hall and id.seatNum =:seatNum and id.rowNum =:rowNum");
        query.setParameter("hall", seat.getHall().getId());
        query.setParameter("seatNum", seat.getSeatNum());
        query.setParameter("rowNum", seat.getRowNum());

        if(query.list().isEmpty()){
            session.save(seat);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Query query = session.createNativeQuery("update seats set category_id =:category where hall_id =:hall " +
                "and row_num =:row and seat_num =:seat ");
        query.setParameter("category", seat.getCategory().getId());
        query.setParameter("hall", seat.getHall().getId());
        query.setParameter("row", seat.getRowNum());
        query.setParameter("seat", seat.getSeatNum());
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        session.createNativeQuery("delete from seats where hall_id =:hall and row_num =:row " +
                "and seat_num =:seat").setParameter("hall", seat.getHall().getId())
                .setParameter("row",seat.getRowNum()).setParameter("seat",seat.getSeatNum()).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("delete from seats").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('seats', RESEED, 0)").executeUpdate();
        transaction.commit();
        session.close();
    }

    private List<Seat> seats = new ArrayList<>();
    @Override
    public List<Seat> view() {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        seats = session.createQuery("from Seat ").list();

        transaction.commit();
        session.close();

        return seats;
    }

    @Override
    public List<Seat> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        seats = session.createQuery("from Seat where hall.id =" + searchId).list();

        transaction.commit();
        session.close();

        return seats;
    }

    @Override
    public List<Seat> searchCategory(int idHall, int row, int seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        seats = session.createQuery("from Seat where hall.id =" + idHall + " AND id.rowNum =" + row + " " +
                " AND id.seatNum =" + seat).list();
        transaction.commit();
        session.close();

        return seats;
    }
}
