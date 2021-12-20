package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import com.jfoenix.controls.*;
import coursework.interfaces.MyListener;
import coursework.models.Check;
import coursework.models.OrderProduct;
import coursework.operations.Notification;
import coursework.operations.Open;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import coursework.models.Product;
import coursework.models.ProductType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

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
    private JFXTextField searchProduct;

    @FXML
    private TableView<ProductType> tableProductType;

    @FXML
    private TableColumn<ProductType, Integer> numColumn;

    @FXML
    private TableColumn<ProductType, String> nameTypeColumn;

    @FXML
    private JFXButton addType;

    @FXML
    private JFXButton editType;

    @FXML
    private JFXButton delType;

    @FXML
    private JFXComboBox<Integer> numberTypeProduct;

    @FXML
    private JFXTextField nameType;

    @FXML
    private TableView<Check> tableCheck;

    @FXML
    private TableColumn<Check, Integer> numberOrderColumn;

    @FXML
    private TableColumn<Check, String> staffColumn;

    @FXML
    void onAction_NumberType(ActionEvent event) {
        for (int i = 0; i < productTypes.size(); i++) {
            if (productTypes.get(i).getId() == numberTypeProduct.getValue()) {
                nameType.setText(productTypes.get(i).getProductName());
            }
        }
    }

    @FXML
    void onClicked_AddType(MouseEvent event) {
        try {
            if (!nameType.getText().isEmpty()) {
                notification.alert = new JFXAlert((Stage) addType.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();

                    try {
                        client.AddNewProductType(new ProductType(nameType.getText()));
                        notification.getSuccess(addType, notification.HEAD_ADD, notification.SUCCESS_ADD);
                        fillingProductType();
                    } catch (RemoteException e) {
                        notification.getError(addType, notification.HEAD_ADD, notification.ERROR_CONNECT);
                    }
                });
            } else {
                notification.getError(addType, notification.HEAD_ADD, notification.ERROR);
            }
        } catch (NullPointerException e) {
            notification.getError(addType, notification.HEAD_ADD, notification.ERROR);
        }
    }

    private Notification notification = new Notification();
    @FXML
    void onClicked_DelType(MouseEvent event) throws RemoteException {
        try {
            if (!numberTypeProduct.getValue().equals("")) { }
            notification.alert = new JFXAlert((Stage) delType.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteProductType(numberTypeProduct.getValue());

                    notification.getSuccess(delType, notification.HEAD_DEL, notification.SUCCESS_DEL);
                    fillingProductType();

                } catch (RemoteException e) {
                    notification.getError(delType, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(delType, notification.HEAD_DEL,notification.ERROR);
        }
    }

    @FXML
    void onClicked_EditType(MouseEvent event) {
        try {
            if (!numberTypeProduct.getValue().equals("") || !nameType.getText().isEmpty()) {
                notification.alert = new JFXAlert((Stage) editType.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();

                    try {
                        client.EditProductType(new ProductType(numberTypeProduct.getValue(), nameType.getText()));
                        notification.getSuccess(editType, notification.HEAD_EDIT, notification.SUCCESS_EDIT);
                        fillingProductType();

                    } catch (RemoteException e) {
                        notification.getError(editType, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                    }
                });
            } else {
                notification.getError(editType, notification.HEAD_EDIT, notification.ERROR);
            }
        } catch (NullPointerException e) {
            notification.getError(editType, notification.HEAD_EDIT, notification.ERROR);
        }
    }

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

        try {
            if (!name.getText().isEmpty() || !category.getValue().isEmpty() ||
                    !total.getText().isEmpty() || !price.getText().isEmpty()) {
                notification.alert = new JFXAlert((Stage) addProduct.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();

                    try {
                        ProductType productType = new ProductType();
                        productType.setId(searchCategoryId(category.getValue()));
                        client.AddNewProduct(new Product(productType, name.getText(),Integer.parseInt(total.getText()), Long.parseLong(price.getText())));
                        fillingProduct();
                        notification.getSuccess(addProduct, notification.HEAD_ADD, notification.SUCCESS_ADD);
                    } catch (RemoteException e) {
                        notification.getError(addProduct, notification.HEAD_ADD, notification.ERROR_CONNECT);
                    }
                });
            } else {
                notification.getError(addProduct, notification.HEAD_ADD, notification.ERROR);
            }
        } catch (NullPointerException e) {
            notification.getError(addProduct, notification.HEAD_ADD, notification.ERROR);
        }
    }

    private int searchCategoryId(String name) {
        for (int i = 0; i < productTypes.size(); i++) {
            if(productTypes.get(i).getProductName().equals(name)) {
                return productTypes.get(i).getId();
            }
        }
        return 0;
    }

    @FXML
    void onMouseClicked_Del(MouseEvent event) throws RemoteException {
        try {
            if (!number.getValue().equals("")) { }
            notification.alert = new JFXAlert((Stage) delProduct.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteProduct(number.getValue());
                    fillingProduct();
                    notification.getSuccess(delProduct, notification.HEAD_DEL, notification.SUCCESS_DEL);

                } catch (RemoteException e) {
                    notification.getError(delProduct, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(delProduct, notification.HEAD_DEL,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_Edit(MouseEvent event) throws RemoteException {
        try {
            if (!number.getValue().equals("") || !name.getText().isEmpty() || !category.getValue().isEmpty() ||
                    !total.getText().isEmpty() || !price.getText().isEmpty()) {
                notification.alert = new JFXAlert((Stage) editProduct.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();

                    try {
                        ProductType productType = new ProductType();
                        productType.setId(searchCategoryId(category.getValue()));
                        client.EditProduct(new Product(number.getValue(),productType, name.getText(),Integer.parseInt(total.getText()), Long.parseLong(price.getText())));
                        fillingProduct();
                        notification.getSuccess(editProduct, notification.HEAD_EDIT, notification.SUCCESS_EDIT);
                    } catch (RemoteException e) {
                        notification.getError(editProduct, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                    }
                });
            } else {
                notification.getError(editProduct, notification.HEAD_EDIT, notification.ERROR);
            }
        } catch (NullPointerException e) {
            notification.getError(editProduct, notification.HEAD_EDIT, notification.ERROR);
        }
    }

    private MyListener myListener;

    private ObservableList<ProductType> productTypes = FXCollections.observableArrayList();
    private List<Product> products = new ArrayList<>();
    private ObservableList<Product> tempData = FXCollections.observableArrayList();
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    BillingClient client = new BillingClient();

    @FXML
    void initialize() throws IOException {

        initMenu(anchor);

        fillingProduct();
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

        fillingCheckTable();
        fillingOrderTable();
    }

    private void fillingProductType() throws RemoteException {

        productTypes = FXCollections.observableArrayList(client.getProductTypeList());

        for(int i = 0; i < productTypes.size(); i++) {
            category.getItems().add(productTypes.get(i).getProductName());
        }

        for (int i = 0; i < productTypes.size(); i++) {
            numberTypeProduct.getItems().add(productTypes.get(i).getId());
        }

        if (productTypes.size() > 0) {
            myListener = (productType,product) -> {
                grid.getChildren().clear();
                fillingProduct(productType.getProductName());
            };
            try {
                initFXML(productTypes, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fillingProductTypeTable(productTypes);
    }

    private int column;
    private int row;

    private void initFXML(List list, int type) throws IOException {
        column = 0;
        row = 1;
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

                    if (column == 4) {
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

    private List<Integer> count = new ArrayList<>();
    private List<Integer> idProduct = new ArrayList<>();
    private List<Long> priceProduct = new ArrayList<>();

    private void fillingProduct(String name) throws IOException {
        products = new ArrayList<>();
        products.add(new Product());
        products.addAll(client.getProductList(name));

        if (products.size() > 0) {
        myListener = (productType,product) -> {
            if(product.getProductName() == null) {
                grid.getChildren().clear();
                fillingProductType();
            }
            else {
                boolean check = true;
                for (int i = 0; i < list.getItems().size(); i++) {
                    if (list.getItems().get(i).contains(product.getProductName())) {
                        count.set(i, 1 + count.get(i));
                        list.getItems().set(i,product.getProductName() + " " + count.get(i) + "шт. цена: " + priceProduct.get(i) * count.get(i) + " руб." );
                        check = false;
                    }
            }
                if (check) {
                    count.add(1);
                    priceProduct.add(product.getPrice());
                    idProduct.add(product.getId());
                    list.getItems().add(product.getProductName() + " 1шт. цена: " + product.getPrice() + " руб.");
                }
                getMoney();
            }

            };
            initFXML(products, 1);
        }

    }

    private Session session = Session.getInstance();

    private void getMoney() {
        Long money = 0L;
        for (int i = 0; i < priceProduct.size(); i++) {
            money += priceProduct.get(i) * count.get(i);
        }
        buy.setText(money + " руб.");
        session.setPrice(money);
    }

    @FXML
    private JFXButton buy;

    @FXML
    private JFXButton plus;

    @FXML
    private JFXButton minus;

    @FXML
    void onClicked_List(MouseEvent event) {
        if (list.getSelectionModel().getSelectedItem() != null) {
            plus.setDisable(false);
            minus.setDisable(false);
        } else {
            plus.setDisable(true);
            minus.setDisable(true);
        }
    }

    private void getCount(Integer operation) {
        list.requestFocus();
        String[] name = list.getSelectionModel().getSelectedItem().split(" ");
        String productName = null;
        for (String str : name) {
            productName = str;
            break;
        }
        for (int i = 0; i < list.getItems().size(); i++) {
            if (list.getItems().get(i).contains(list.getSelectionModel().getSelectedItem())) {
                if (operation == 1) {
                    count.set(i, count.get(i) - 1);
                    if (count.get(i) == 0) {
                        count.remove(i);
                        priceProduct.remove(i);
                        idProduct.remove(i);
                        list.getItems().remove(i);
                    }
                } else {
                    count.set(i, 1 + count.get(i));
                }

                try {
                    list.getItems().set(i, productName + " " + count.get(i) + "шт. цена: " + priceProduct.get(i) * count.get(i) + " руб.");
                } catch (IndexOutOfBoundsException e) { }
            }
        }
        getMoney();
    }
    @FXML
    void onClicked_Minus(MouseEvent event) {
        getCount(1);
    }

    @FXML
    void onClicked_Plus(MouseEvent event) {
        getCount(0);
    }

    @FXML
    void onClicked_Buy(MouseEvent event) throws IOException {
        session.setProductId(idProduct);
        session.setCount(count);
        initFXMLWindow(buy, "/coursework/controllers/payBuffet.fxml", "Заказ", 0,0);
    }

    @FXML
    private JFXButton reset;

    @FXML
    void onClicked_Reset(MouseEvent event) {
        count.clear();
        list.getItems().clear();
    }

    private void fillingProduct() throws RemoteException {
        number.getItems().clear();
        productObservableList = FXCollections.observableArrayList(client.getProductList());
        for(int i = 0; i < productObservableList.size(); i++) {
            number.getItems().add(productObservableList.get(i).getId());
        }
        fillingProductTable(productObservableList);
    }

    private void fillingProductTable(ObservableList<Product> productObservableList) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableProduct.setItems(productObservableList);
    }

    private void fillingProductTypeTable(ObservableList<ProductType> productTypes) {
        numColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTypeColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableProductType.setItems(productTypes);
    }

    private ObservableList<Check> checks = FXCollections.observableArrayList();

    private void fillingCheckTable() throws RemoteException {
        checks = FXCollections.observableArrayList(client.getCheckList());
        numberOrderColumn.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
        staffColumn.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
        tableCheck.setItems(checks);
    }

    @FXML
    private TableView<OrderProduct> tableOrder;

    @FXML
    private TableColumn<OrderProduct, Integer> numOrderColumn;

    @FXML
    private TableColumn<OrderProduct, Instant> dateColumn;

    @FXML
    private TableColumn<OrderProduct, String> paymentColumn;

    @FXML
    private TableColumn<OrderProduct, Long> moneyColumn;

    private ObservableList<OrderProduct> orderProducts = FXCollections.observableArrayList();

    private void fillingOrderTable() throws RemoteException {
        orderProducts = FXCollections.observableArrayList(client.getOrderList());
        numOrderColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        tableOrder.setItems(orderProducts);
    }

}
