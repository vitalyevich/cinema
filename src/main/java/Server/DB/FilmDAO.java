package Server.DB;

import Server.Model.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmDAO {

    public void save(Film film) throws SQLException;
    public void update(Film film);
    public void deleteByName(String name);
    public void deleteById(int id);
    public void truncate();
    public List<Film> view();
    public List<Film> search(int searchId);
}
