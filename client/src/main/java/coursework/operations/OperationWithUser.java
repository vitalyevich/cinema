package coursework.operations;

import coursework.models.Access;
import coursework.models.Staff;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface OperationWithUser {

    public void AddNewStaff(Staff staff, Access access) throws RuntimeException, SQLException, RemoteException;
    public void EditStaff(Staff staff, Access access) throws RuntimeException, SQLException, RemoteException;
    public void DeleteStaff(int id ) throws RuntimeException, RemoteException;
}
