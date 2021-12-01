package Server.DB;

import Server.Model.Genre;
import Server.Model.GenresName;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {

    public void save(Genre genre) throws SQLException;
    public void update(Genre genre);
    public void delete(int id);
    public void truncate();
    public List<Genre> view();
    public List<Genre> search(int searchId);
    public void addTotal(GenresName genresName);
    public List<GenresName> searchGenre(int searchId);
}
