package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import coursework.interfaces.MyListener;
import coursework.models.Product;
import coursework.models.ProductType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    void click(MouseEvent event) throws IOException {
        myListener.onClickListener(productType, product);
    }

    private ProductType productType = new ProductType();
    private Product product = new Product();

    private MyListener myListener;

    public static final String CURRENCY = " руб";

    public void setDataProductType(ProductType productType, MyListener myListener) {
        this.productType = productType;
        this.myListener = myListener;
        nameLabel.setText(productType.getProductName());
        priceLabel.setText("");

    }

    public void setDataProduct(Product product, MyListener myListener) {
        this.product = product;
        this.myListener = myListener;
            try {
                product.getId().equals(null);
                nameLabel.setText(product.getProductName());
                priceLabel.setText(product.getPrice() + CURRENCY);
            } catch (NullPointerException e) {
                nameLabel.setText("");
                priceLabel.setText("");
            }

    }

    @FXML
    void initialize() {

    }

}
