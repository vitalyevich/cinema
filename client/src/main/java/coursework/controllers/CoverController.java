package coursework.controllers;

import coursework.operations.MyTimer;
import coursework.operations.Open;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CoverController extends Open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label control;

    @FXML
    private JFXButton buttonSetting;

    @FXML
    private JFXButton buttonExit;

    @FXML
    private Label labelName;

    @FXML
    private Label dateTime;

    @FXML
    private Label timer;

    @FXML
    void onMouseClicked_Exit(MouseEvent event) throws IOException {
        initFXMLWindow(buttonExit, "/coursework/controllers/administration.fxml","Панель администратора",1, 0);
    }

    @FXML
    void onMouseClicked_Setting(MouseEvent event) throws IOException {
        initFXMLWindow(buttonExit, "settings.fxml","Настройки",0, 0);
    }

    private long count = 0;

    private Session session = Session.getInstance();
    @FXML
    void initialize() {
        new MyTimer(count, dateTime, timer);
        connectivity();

        labelName.setText(session.getUser());
    }

    private BillingClient client;

    public void connectivity() {
        new Thread( () -> {
            while (true) {

                if ((client = new BillingClient()).checkConnectivity()) {
                    control.setText("*Подключен*");
                    control.setStyle("-fx-text-fill: green");
                }
                else {
                    control.setText("*Соединение потеряно*");
                    control.setStyle("-fx-text-fill: red");
                }

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
