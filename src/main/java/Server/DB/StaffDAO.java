package Server.DB;

import Server.Model.Access;
import Server.Model.Department;
import Server.Model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface StaffDAO {

    public void save(Staff staff) throws SQLException;
    public void update(Staff staff);
    public void delete(String lastName, String firstName, String middleName) throws SQLException;
    public void truncate();
    public void deleteById(int id);
    public void deleteByPosition(Department role);
}
