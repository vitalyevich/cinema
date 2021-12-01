package Server.DB;

import Server.Model.Hall;

import java.sql.SQLException;
import java.util.List;

public interface HallDAO {

    public void save(Hall hall) throws SQLException;
    public void update(Hall hall);
    public void delete(int id);
    public void truncate();
    public List<Hall> view();
    public List<Hall> search(int searchId);
}
