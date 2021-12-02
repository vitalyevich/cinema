package Client.Administration;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import Client.Interfaces.MyListener;
import Client.Model.Open;
import Client.RMI.BillingClient;
import Server.Model.Product;
import Server.Model.ProductType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class BuffetController extends Open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private JFXListView<String> list;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableColumn<Product, Integer> numberColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, Integer> totalColumn;

    @FXML
    private TableColumn<Product, Long> priceColumn;

    @FXML
    private JFXComboBox<Integer> number;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXTextField total;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXButton addProduct;

    @FXML
    private JFXButton editProduct;

    @FXML
    private JFXButton delProduct;

    @FXML
    private JFXButton clearProduct;

    @FXML
    private JFXTextField searchProduct;

    @FXML
    private TableView<ProductType> tableProductType;

    @FXML
    private TableColumn<ProductType, Integer> numColumn;

    @FXML
    private TableColumn<ProductType, String> nameTypeColumn;


    @FXML
    void onAction_Number(ActionEvent event) throws RemoteException {

        List<Product> prod = client.getProductList(number.getValue());
        name.setText(prod.get(0).getProductName());
        category.getSelectionModel().select(prod.get(0).getType().getId() - 1);
        total.setText(prod.get(0).getAmount() + "");
        price.setText(prod.get(0).getPrice() + "");

    }

    @FXML
    void onMouseClicked_Add(MouseEvent event) throws RemoteException {
        client.AddNewProduct(new Product());
    }

    @FXML
    void onMouseClicked_Clear(MouseEvent event) throws RemoteException {
        client.TruncateTableProduct();
    }

    @FXML
    void onMouseClicked_Del(MouseEvent event) throws RemoteException {
       client.DeleteProductById(number.getValue());
    }

    @FXML
    void onMouseClicked_Edit(MouseEvent event) throws RemoteException {
        client.EditProduct(new Product());
    }

    private MyListener myListener;

    private ObservableList<ProductType> productTypes = FXCollections.observableArrayList();
    private List<Product> products = new ArrayList<>();
    private ObservableList<Product> tempData = FXCollections.observableArrayList();
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    private List<ProductType> getDataProductType() throws RemoteException {
        return client.getProductTypeList();
    }

    private List<Product> getDataProduct(String name) throws  RemoteException {
        return client.getProductList(name);
    }

    private List<Product> getDataProduct() throws  RemoteException {
        return client.getProductList();
    }

    BillingClient client = new BillingClient();

    @FXML
    void initialize() throws IOException {

        initMenu(anchor);

        productTypes.addAll(getDataProductType());
        productObservableList.addAll(getDataProduct());

        for(int i = 0; i < productObservableList.size(); i++) {
            number.getItems().add(productObservableList.get(i).getId());
        }

        for(int i = 0; i < productTypes.size(); i++) {
            category.getItems().add(productTypes.get(i).getProductName());
        }

        fillingProductTable(productObservableList);
        fillingProductTypeTable();
        fillingProductType();

        searchProduct.textProperty().addListener((obs, oldText, newText) -> {
            if (!searchProduct.getText().isEmpty()) {
                tempData = FXCollections.observableArrayList();

                for (int i = 0; i < productObservableList.size(); i++) {
                    if (productObservableList.get(i).getProductName().toLowerCase().contains(searchProduct.getText().toLowerCase().trim())) {
                        tempData.add(productObservableList.get(i));
                    }
                }
                fillingProductTable(tempData);
            } else {
                fillingProductTable(productObservableList);
            }
        });
    }

    private void fillingProductType() throws RemoteException {

        if (productTypes.size() > 0) {
            myListener = productType -> {
                grid.getChildren().clear();
                fillingProduct(productType.getProductName());
            };
            try {
                initFXML(productTypes, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int column = 0;
    private int row = 1;

    private void initFXML(List list, int type) throws IOException {
        new Thread( () -> {
            Platform.runLater(() -> {
                for (int i = 0; i < list.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                    AnchorPane anchorPane = null;
                    try {
                        anchorPane = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ItemController itemController = fxmlLoader.getController();
                    if (type == 0) {
                        itemController.setDataProductType((ProductType) list.get(i), myListener);
                    } else {
                        itemController.setDataProduct((Product) list.get(i), myListener);
                    }

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row);

                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);


                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            });
        }).start();
    }

    private void fillingProduct(String name) throws IOException {
        products.addAll(getDataProduct(name));
        if (products.size() > 0) {

        myListener = productType -> {

                //list.getItems().add(productType.getName());

                productType.getProductName();

            };
            initFXML(products, 1);
        }

    }

    private void fillingProductTable(ObservableList<Product> productObservableList) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableProduct.setItems(productObservableList);
    }

    private void fillingProductTypeTable() {
        numColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTypeColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableProductType.setItems(productTypes);
    }
}
