package Client.Interfaces;

import Server.Model.ProductType;

import java.io.IOException;
import java.rmi.RemoteException;

public interface MyListener {

    public void onClickListener(ProductType productType) throws IOException;
}
