package Server.DB;

import Server.Model.Film;
import Server.Model.Seance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SeanceDAO {

    public void save(Seance seance) throws SQLException;
    public void update(Seance seance);
    public void deleteById(int id);
    public void deleteByName(int id);
    public void deleteByDate(LocalDate date, LocalTime time);
    public void truncate();
    public List<Seance> view();
    public List<Seance> search(int searchId);
}
