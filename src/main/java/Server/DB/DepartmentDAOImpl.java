package Server.DB;

import Server.Model.Access;
import Server.Model.AccessId;
import Server.Model.Department;
import Server.Model.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO{

    @Override
    public List<Department> findByDepartment(String department) {
        return null;
    }

    @Override
    public void save(Department department) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Department WHERE position = :position");
        query.setParameter("position", department.getPosition());

        if(query.list().isEmpty()){
            session.save(department);
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void updateSalary(Department department) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Department department1 = (Department) session.get(Department.class, department.getId());
        department1.setSalary(department.getSalary());
        session.update(department1);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateDep(Department department) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Department department1 = (Department) session.get(Department.class, department.getId());
        department1.setPosition(department.getPosition());
        session.update(department1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Department department = (Department) session.get(Department.class, id);
        session.delete(department);
        transaction.commit();
        session.close();
    }

    @Override
    public void truncate() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("delete from departments").executeUpdate();
        session.createNativeQuery("DBCC CHECKIDENT ('departments', RESEED, 0)").executeUpdate();
        Department department = new Department();
        session.save(new Department("Разработчик", 2100L));

        session.createNativeQuery("DBCC CHECKIDENT ('staff', RESEED, 0)").executeUpdate();
        session.save(new Staff("Садовский","Максим","Витальевич",
                "8(033)-785-53-15",'М')); // access

        Staff staff = new Staff();
        staff.setId(1);

        department.setId(1);

        session.save(new Access(new AccessId(staff, department),
                "$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G"));

        transaction.commit();
        session.close();
    }

    @Override
    public List<Department> view() {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <Department> departments = session.createQuery("FROM Department").list();

        transaction.commit();
        session.close();
        return departments;
    }
}
