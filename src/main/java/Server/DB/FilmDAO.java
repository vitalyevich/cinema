package Server.DB;

import Server.Model.Film;

import java.util.List;

public interface FilmDAO {

    public Film findById(int id);
    public List<Film> findByFilm(String film);
    public void save(Film film);
    public void update(Film film);
    public void deleteByName(String name);
    public void deleteById(int id);
    public void truncate();
    public List<Film> view();
    public List<Film> search(int searchId);
}
