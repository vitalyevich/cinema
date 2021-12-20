package coursework.interfaces;

import coursework.models.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {

    public void save(Category category) throws SQLException;
    public void update(Category category);
    public void delete(int id);
    public void truncate();
    public List<Category> view();
    public List<Category> search(int searchId);
}
