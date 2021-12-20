package coursework.interfaces;

import coursework.models.Product;
import coursework.models.ProductType;

import java.util.List;

public interface BuffetDAO {

    public void saveProduct(Product product);
    public void updateProduct(Product product);
    public void saveProductType(ProductType product);
    public void updateProductType(ProductType product);
    public void deleteProduct(int id);
    public void deleteType(int id);
    public void truncate();
    public List<Product> viewProduct(String name);
    public List<Product> viewProduct();
    public List<Product> viewProduct(int searchId);
    public List<ProductType> viewProductType();
}
