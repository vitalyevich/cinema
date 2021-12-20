package coursework.interfaces;

import coursework.models.Access;

import java.util.List;

public interface AccessDAO {

    public List<Access> findByAccess(String lastName, String password);
    public void save(Access access);
    public List<Access> view();
    public List<Access> search(int searchId);
    public void update(Access access);
}
