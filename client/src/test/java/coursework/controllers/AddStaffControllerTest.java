package coursework.controllers;

import coursework.models.Access;
import coursework.models.Staff;
import coursework.operations.OperationWithUserImpl;
import coursework.rmi.BillingClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.sql.SQLException;

class AddStaffControllerTest {

    @Test
    void AddNewStaff_RemoteException() {
        OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
        try {
            operationWithUser.AddNewStaff(new Staff(), new Access());
        } catch (SQLException e) { } catch (RemoteException e) {
            Assert.fail("Соединение с сервером не установлено");
        }
    }

}