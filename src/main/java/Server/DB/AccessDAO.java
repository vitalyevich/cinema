package Server.DB;

import Server.Model.Access;

import java.util.List;

public interface AccessDAO {

    public List<Access> findByAccess(String lastName, String password);
    public void save(Access access);
    public List<Access> view();
    public List<Access> search(int searchId);
}
