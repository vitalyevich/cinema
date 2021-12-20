package coursework.operations;

import coursework.interfaces.OrderDAO;
import coursework.models.Check;
import coursework.models.Order;
import coursework.models.OrderProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(order);

        transaction.commit();
        session.close();
        return order.getId();
    }

    @Override
    public void saveOrderProduct(OrderProduct orderProduct) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(orderProduct);

        transaction.commit();
        session.close();
    }

    @Override
    public void saveOrderCheck(Check check) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(check);

        transaction.commit();
        session.close();
    }


    @Override
    public List<Check> viewCheck() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <Check> check = session.createQuery("FROM Check").list();

        transaction.commit();
        session.close();
        return check;
    }

    @Override
    public List<OrderProduct> viewOrder() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <OrderProduct> orderProducts = session.createQuery("FROM OrderProduct").list();

        transaction.commit();
        session.close();
        return orderProducts;
    }
}
