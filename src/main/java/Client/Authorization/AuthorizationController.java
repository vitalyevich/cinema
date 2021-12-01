package Client.Authorization;

import Client.Interfaces.Verifiable;
import Client.RMI.BillingClient;
import Server.Model.Access;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import Client.Model.Open;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorizationController extends Open implements Verifiable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton buttonEnter;

    @FXML
    private JFXTextField textLogin;

    @FXML
    private JFXPasswordField textPassword;

    @FXML
    private Label labelError;

    @FXML
    private Label labelConnect;

    @FXML
    private FontAwesomeIcon iconConnect;

    private volatile boolean isRunning = false;

    private int counter = 0;

    @FXML
    void buttonAction_Enter(ActionEvent event) {
        if (!isRunning) {
            authorization();
        }
    }

    private List<Access> accesses = new ArrayList<>();

    private Access access = new Access();

    @FXML
    void buttonPressed_Enter(KeyEvent event) {
        keyEnter(event);
    }

    @FXML
    void textPressed_Login(KeyEvent event) {
        keyEnter(event);
    }

    @FXML
    void textPressed_Pass(KeyEvent event) {
        keyEnter(event);
    }

    private void keyEnter(KeyEvent event)
    {
        if (event.getCode().equals(KeyCode.ENTER)) {
            buttonEnter.fire();
        }
    }

    @Override
    public void validator() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Логин пустой!");
        textLogin.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Пароль пустой!");
        textPassword.getValidators().add(validator);

        textLogin.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                textLogin.validate();
            }
        });
        textPassword.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                textPassword.validate();
            }
        });
    }

    private BillingClient client;

    public void connectivity() {

        new Thread( () -> {
            while (true) {

                if (!(client = new BillingClient()).checkConnectivity()) {
                    Platform.runLater(() -> {
                        labelConnect.setText("сервер отключен!");
                        buttonEnter.setDisable(true);
                        iconConnect.setOpacity(1);
                    });
                }
                else {
                    Platform.runLater(() -> {
                        labelConnect.setText("");
                        iconConnect.setOpacity(0);
                        buttonEnter.setDisable(false);
                    });
                }
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    void initialize() {

        connectivity();
        validator();
    }

    private void authorization() {
        checkUser(textLogin.getText(), textPassword.getText());
    }

    private int i;

    public void setTimer() {

        if (counter == 5) {

            i = 30;
            new Thread( () -> {

                isRunning = true;
                while(i >= 0) {
                        try {
                            Platform.runLater(() -> {
                                labelError.setText("Повторите попытку через " + i + " секунд!");

                                if (i == 0) {
                                    labelError.setText("");
                                }
                                --i;
                            });
                            Thread.sleep(1000L);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                isRunning = false;
                counter = 3;
            }).start();
        }
    }

    private void checkUser(String lastName, String password) {

            new Thread( () -> {
                try {

                    if (!lastName.isEmpty() || !password.isEmpty()) {

                        accesses = client.findByAccess(lastName, password);

                        if (!(accesses == null)) {

                                Platform.runLater(() -> {
                                    labelError.setText("");
                                    counter = 0;

                                    int roleId = accesses.get(0).getId().getDepartmentId().getId();
                                    try {
                                        menu(roleId);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    Thread.interrupted();
                                });
                            }
                    }
                    else {
                      throw new IndexOutOfBoundsException();
                    }
                    setTimer();

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                catch (IndexOutOfBoundsException e) {
                    Platform.runLater(() -> {
                        labelError.setText("Вы ввели неверно логин или пароль!");
                        ++counter;
                        setTimer();
                    });
                }
            }).start();
    }

    private final int ADMINISTRATOR = 1;
    private final int MANAGER = 2;
    private final int CASHIER = 3;
    private final int SALESMAN = 4;
    private final int FAIL = 0;

    private void menu(int roleId) throws IOException {

        switch (roleId) {
            case FAIL: break;
            case ADMINISTRATOR: {
                initFXMLWindow(buttonEnter, "administration.fxml","Панель администратора",1, 0);
                break;
            }
            case MANAGER: break;
            case CASHIER: break;
            case SALESMAN: break;
        }
    }
}
