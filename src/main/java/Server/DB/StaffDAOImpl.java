package Server.DB;

import Server.Model.Access;
import Server.Model.AccessId;
import Server.Model.Department;
import Server.Model.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {

    private List<Staff> staffList = new ArrayList<>();

    @Override
    public void save(Staff staff) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Staff WHERE lastName = :lastName AND firstName = :firstName " +
                "AND middleName =: middleName");

        query.setParameter("lastName", staff.getLastName());
        query.setParameter("firstName", staff.getFirstName());
        query.setParameter("middleName", staff.getMiddleName());

        staffList = query.list();

        if(staffList.isEmpty()) {
            session.save(staff);
        }
        else {
            throw new SQLException();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Staff staff) { // переделать
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Staff staff1 = (Staff) session.get(Staff.class, staff.getId());
        staff1.setFirstName(staff.getFirstName());
        staff1.setLastName(staff.getLastName());
        staff1.setMiddleName(staff.getMiddleName());
        staff1.setPhone(staff.getPhone());
        staff1.setGender(staff.getGender());

        session.update(staff1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(String lastName, String firstName, String middleName) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        if (lastName.equals("Садовский") && firstName.equals("Максим") && middleName.equals("Витальевич")) {
            throw new SQLException();
        }

        Query query = session.createQuery("DELETE FROM Staff WHERE lastName = :lastname AND firstName = :firstname " +
                "AND middleName =: middlename");
        query.setParameter("lastname", lastName);
        query.setParameter("firstname", firstName);
        query.setParameter("middlename", middleName);

        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() { // переделать
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        session.createNativeQuery("delete from Staff").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('staff', RESEED, 0)").executeUpdate();
        session.save(new Staff("Садовский","Максим","Витальевич",
                "8(033)-785-53-15",'М'));

        Staff staff = new Staff();
        staff.setId(1);

        Department department = new Department();
        department.setId(1);

        session.save(new Access(new AccessId(staff, department),
                "$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G"));
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Staff staff = (Staff) session.get(Staff.class, id);
        session.delete(staff);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteByPosition(Department role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query query = session.createNativeQuery("DELETE FROM staff WHERE id IN (SELECT staff_id FROM access " +
                "WHERE department_id =:role)");
        query.setParameter("role", role.getId());
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
