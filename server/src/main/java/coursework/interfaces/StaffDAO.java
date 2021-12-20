package coursework.interfaces;

import coursework.models.Department;
import coursework.models.Staff;

import java.sql.SQLException;

public interface StaffDAO {

    public int save(Staff staff) throws SQLException;
    public void update(Staff staff);
    public void truncate();
    public void deleteById(int id);
    public void deleteByPosition(Department role);
}
