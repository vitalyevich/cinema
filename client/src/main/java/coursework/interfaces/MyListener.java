package coursework.interfaces;

import coursework.models.Product;
import coursework.models.ProductType;

import java.io.IOException;

public interface MyListener {

    public void onClickListener(ProductType productType, Product product) throws IOException;
}
