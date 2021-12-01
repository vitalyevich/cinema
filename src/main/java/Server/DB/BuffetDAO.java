package Server.DB;

import Server.Model.Product;
import Server.Model.ProductType;
import javafx.collections.ObservableList;

import java.util.List;

public interface BuffetDAO {

    public void save(Product product);
    public void update(Product product);
    public void deleteById(int id);
    public void deleteByName(String name);
    public void truncate();
    public List<Product> viewProduct(String name);
    public List<Product> viewProduct();
    public List<Product> viewProduct(int searchId);
    public List<ProductType> viewProductType();
}
