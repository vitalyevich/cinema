package coursework.operations;

import coursework.interfaces.AccessDAO;
import coursework.models.Access;
import coursework.models.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class AccessDAOImpl implements AccessDAO {

    private List<Access> accesses = new ArrayList<>();

    @Override
    public List<Access> findByAccess(String lastName, String password) {

        boolean check = true;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        accesses = session.createQuery("from Access where id.staffId.lastName =:lastName")
                .setParameter("lastName", lastName).list();

        if (!accesses.isEmpty()) {

            if(!BCrypt.checkpw(password, accesses.get(0).getPassword())) {
                check = false;
            }
        }
        transaction.commit();
        session.close();

        if (!check) {
            accesses.clear();
        }
        else {
            accesses.get(0).setPassword("");
        }

        return accesses;
    }

    @Override
    public void save(Access access) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(access);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Access> view() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        accesses =  session.createQuery("from Access ").list();

        transaction.commit();
        session.close();

        return accesses;
    }

    @Override
    public List<Access> search(int searchId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        accesses = session.createQuery("from Access where id.staffId.id =" + searchId).list();

        transaction.commit();
        session.close();

        return accesses;
    }

    @Override
    public void update(Access access) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

         session.createNativeQuery("UPDATE access SET department_id =:department, password =:password WHERE staff_id=:staff")
                .setParameter("password", access.getPassword()).setParameter("department", access.getId().getDepartmentId().getId())
                 .setParameter("staff", access.getId().getStaffId().getId()).executeUpdate();

        transaction.commit();
        session.close();
    }
}
