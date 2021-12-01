package Server.DB;

import Server.Model.Category;
import Server.Model.Product;
import Server.Model.ProductType;
import Server.Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BuffetDAOImpl implements BuffetDAO{

    @Override
    public void save(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(product);

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Product product1 = (Product) session.get(Product.class, product.getId());
        product1.setProductName(product.getProductName());
        product1.setType(product.getType());
        product1.setAmount(product.getAmount());
        product1.setPrice(product.getPrice());
        session.update(product1);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Product product = (Product) session.get(Product.class, id);
        session.delete(product);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query query = session.createNativeQuery("delete from products where product_name =:name");
        query.setParameter("name", name);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {

    }


    @Override
    public List<Product> viewProduct(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <Product> product = session.createQuery("from Product where type.productName =:name")
                .setParameter("name", name).list();

        transaction.commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> viewProduct() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <Product> product = session.createQuery("from Product").list();

        transaction.commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> viewProduct(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <Product> product = session.createQuery("from Product where id =:searchId")
                .setParameter("searchId", searchId).list();

        transaction.commit();
        session.close();
        return product;
    }

    @Override
    public List<ProductType> viewProductType() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <ProductType> productTypes = session.createQuery("FROM ProductType ").list();

        transaction.commit();
        session.close();
        return productTypes;
    }

}
