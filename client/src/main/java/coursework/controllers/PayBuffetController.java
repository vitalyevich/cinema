package coursework.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import coursework.models.*;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PayBuffetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton cash;

    @FXML
    private JFXButton card;

    @FXML
    private JFXButton pay;

    @FXML
    private Label price;

    @FXML
    void onClicked_Pay(MouseEvent event) throws RemoteException {

        Payment payment = new Payment();
        payment.setId(id);
        Long check = 100000L + (long) (Math.random() * 900000L);
        int orderId = client.AddNewOrder(new Order(Instant.now(),payment,check, session.getPrice()));

        OrderProductId orderProductId = new OrderProductId();
        Order order = new Order();
        order.setId(orderId);
        orderProductId.setOrderId(order);
        for (int i = 0; i < session.getProductId().size(); i++) {
            orderProductId.setProductId(session.getProductId().get(i));
            client.AddNewOrderProduct(new OrderProduct(orderProductId, session.getCount().get(i)));
        }

        CheckId checkId = new CheckId();
        checkId.setOrderId(orderId);
        Staff staff = new Staff();
        staff.setId(session.getUserId());
        checkId.setStaffId(staff);
        client.AddNewCheck(new Check(checkId));

        pay.getScene().getWindow().hide();
    }

    private BillingClient client = new BillingClient();
    @FXML
    void onClicked_Card(MouseEvent event) {
        choice(card,2);
    }

    @FXML
    void onClicked_Cash(MouseEvent event) {
        choice(cash,1);
    }

    private Session session = Session.getInstance();

    @FXML
    void initialize() {
        price.setText(session.getPrice() + " руб.");
        id = 1;
    }

    private int id;
    private void choice(JFXButton button, int id) {
        this.id = id;
        card.setStyle("-fx-border-color:  black");
        cash.setStyle("-fx-border-color:  black");
        button.setStyle("-fx-border-color:  black; -fx-background-color:  #A7C7E7");
    }
}
