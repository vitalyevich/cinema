package Client.Administration;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import Client.Interfaces.MyListener;
import Server.Model.Product;
import Server.Model.ProductType;
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
    private ImageView img;

    @FXML
    void click(MouseEvent event) throws IOException {
        myListener.onClickListener(productType);
    }

    private ProductType productType = new ProductType();
    private Product product = new Product();
    private MyListener myListener;

    public static final String CURRENCY = " руб";

    // попробовать не дублировать

    public void setDataProductType(ProductType productType, MyListener myListener) {
        this.productType = productType;
        this.myListener = myListener;
        nameLabel.setText(productType.getProductName());
        priceLabel.setText("");
        Image image = new Image(getClass().getResourceAsStream(productType.getImageLink()));
        img.setImage(image);
    }

    public void setDataProduct(Product product, MyListener myListener) {
        this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getProductName());
        priceLabel.setText(product.getPrice() + CURRENCY);
        Image image = new Image(getClass().getResourceAsStream(product.getImageLink()));
        img.setImage(image);
    }

    @FXML
    void initialize() {

    }

}
