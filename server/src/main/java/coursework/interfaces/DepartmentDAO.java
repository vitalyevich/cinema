package coursework.interfaces;

import coursework.models.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {

    public List<Department> findByDepartment(String department);
    public void save(Department department) throws SQLException;
    public void updateSalary(Department department);
    public void updateDep(Department department);
    public void delete(int id);
    public void truncate();
    public List<Department> view();
}
