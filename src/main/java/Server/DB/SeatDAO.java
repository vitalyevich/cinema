package Server.DB;

import Server.Model.Seat;

import java.sql.SQLException;
import java.util.List;

public interface SeatDAO {

    public void save(Seat seat) throws SQLException;
    public void update(Seat seat);
    public void delete(Seat seat);
    public void truncate();
    public List<Seat> view();
    public List<Seat> search(int searchId);
    public List<Seat> searchCategory(int idHall, int row, int seat);
    public int searchSeatByRow(int row);
}
