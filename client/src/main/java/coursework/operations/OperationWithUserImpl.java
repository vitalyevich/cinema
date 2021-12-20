package coursework.operations;

import coursework.models.Access;
import coursework.models.Staff;
import coursework.rmi.BillingClient;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class OperationWithUserImpl implements OperationWithUser {

    private BillingClient client;
    @Override
    public void AddNewStaff(Staff staff, Access access) throws RuntimeException, SQLException, RemoteException {
        int userId = client.AddNewStaff(staff);
        Staff staff1 = new Staff();
        staff1.setId(userId);
        access.getId().setStaffId(staff1);
        client.AddNewAccess(access);
    }

    @Override
    public void EditStaff(Staff staff, Access access) throws RuntimeException, SQLException, RemoteException {
        client.EditStaff(staff);
        client.EditAccess(access);
    }

    @Override
    public void DeleteStaff(int id) throws RuntimeException, RemoteException {
        client.DeleteStaffById(id);
    }

    public OperationWithUserImpl () {
        this.client = new BillingClient();
    }

}
