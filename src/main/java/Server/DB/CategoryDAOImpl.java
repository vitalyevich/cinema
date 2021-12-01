package Server.DB;

import Server.Model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void save(Category category) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Category WHERE categoryName = :categoryName");
        query.setParameter("categoryName", category.getCategoryName());

        if(query.list().isEmpty()){
            session.save(category);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Category category1 = (Category) session.get(Category.class, category.getId());
        category1.setCategoryName(category.getCategoryName());
        session.update(category1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.delete(category);
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM categories").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('categories', RESEED, 0)").executeUpdate();
        session.save(new Category("VIP"));
        session.save(new Category("Взрослый"));
        session.save(new Category("Детский"));
        transaction.commit();
        session.close();
    }

    private List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> view() {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        categories = session.createQuery("from Category ").list();

        transaction.commit();
        session.close();

        return categories;
    }

    @Override
    public List<Category> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        categories = null;
        categories = session.createQuery("from Category where id =" + searchId).list();

        transaction.commit();
        session.close();

        return categories;
    }
}
