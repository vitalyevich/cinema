package coursework.operations;

import coursework.interfaces.TicketDAO;
import coursework.models.Ticket;
import coursework.models.TicketsPrice;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public List<TicketsPrice> viewPrice() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        List<TicketsPrice> ticketsPrices = session.createQuery("from TicketsPrice ").list();

        transaction.commit();
        session.close();

        return ticketsPrices;
    }

    @Override
    public List<Ticket> viewTicket() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        List<Ticket> ticket = session.createQuery("from Ticket ").list();

        transaction.commit();
        session.close();

        return ticket;
    }
}
