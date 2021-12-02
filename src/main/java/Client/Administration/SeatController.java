package Client.Administration;

import Client.Model.Notification;
import Client.RMI.BillingClient;
import Server.Model.*;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class SeatController extends FilmController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton buttonSave;

    @FXML
    private JFXButton buttonAdd;

    @FXML
    private JFXButton buttonDel;

    @FXML
    private JFXComboBox<Integer> number;

    @FXML
    private JFXComboBox<Integer> row;

    @FXML
    private JFXComboBox<Integer> seat;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXTextField rowText;

    @FXML
    private JFXTextField seatText;

    private Category category1 = new Category();
    private Hall hall = new Hall();
    private SeatId seatId = new SeatId();

    private void setParameter() {
        hall.setId(number.getValue());
        seatId.setSeatNum(seat.getValue());
        seatId.setRowNum(row.getValue());
    }

    @FXML
    void onClicked_Save(MouseEvent event) throws RemoteException {

        try {

            if(number.getValue().equals("") || row.getValue().equals("") || seat.getValue().equals("")
                    || category.getValue().equals("")) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) buttonSave.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();
                try {
                    setParameter();
                    category1.setId(searchCategoryId(category.getValue()));

                    client.EditSeat(new Seat(seatId,hall, category1));
                    buttonSave.getScene().getWindow().hide();

                } catch (RemoteException e) {
                    notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR);
        }
    }

    @FXML
    void onClicked_Add(MouseEvent event) {
        try {

            if (number.getValue().equals("") || rowText.getText().isEmpty() || seatText.getText().isEmpty()
                    || category.getValue().equals("")) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) buttonAdd.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD,notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    setParameter();
                    category1.setId(searchCategoryId(category.getValue()));

                    client.AddNewSeat(new Seat(seatId,hall, category1));
                    buttonAdd.getScene().getWindow().hide();

                } catch (RemoteException e) {
                    notification.getError(buttonAdd,  notification.HEAD_ADD, notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(buttonAdd,  notification.HEAD_ADD, notification.ERROR);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(buttonAdd,  notification.HEAD_ADD, notification.ERROR);
        }
    }

    private Notification notification = new Notification();

    @FXML
    void onClicked_Del(MouseEvent event) throws RemoteException {
        try {

            if (number.getValue().equals("") || row.getValue().equals("") || seat.getValue().equals("")) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) buttonDel.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    setParameter();
                    client.DeleteSeat(new Seat(seatId,hall));

                    buttonDel.getScene().getWindow().hide();

                    notification.getSuccess(buttonDel, notification.HEAD_DEL, notification.SUCCESS_DEL);
                } catch (RemoteException e) {
                    notification.getError(buttonDel, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(buttonDel, notification.HEAD_DEL, notification.ERROR);
        }
    }

    private int searchCategoryId(String name) {
        for (int i = 0; i < categories.size(); i++) {
            if (name.equals(categories.get(i).getCategoryName())) {
                return categories.get(i).getId();
            }
        }
        return 0;
    }

    private List<Seat> seatList;
    @FXML
    void onAction_Number(ActionEvent event) throws RemoteException { // проверок добавить
        seatList = client.getSeat(number.getValue());

        row.getItems().clear();

    // выбрать ряд, выбрать место, выбрать номер залла
        for (int i = 1; i <= seatList.get(seatList.size() - 1).getRowNum(); i++) { // getrowtotal
            row.getItems().add(i);
        }
        //category.getSelectionModel().select(seats.get(0).getCategory().getId() - 1); // fix method
    }

    @FXML
    void onAction_Row(ActionEvent event) { // проверок добавить

        seat.getItems().clear();

        for (int i = 0; i < seatList.size(); i++) {
            if (seatList.get(i).getRowNum() == row.getValue()) {
                seat.getItems().add(seatList.get(i).getSeatNum());
            }
        }

        /*for (int i = 1; i <= seatList.get(seatList.size() - 1).getSeatNum(); i++) {
            seat.getItems().add(i);
        }*/
    }

    @FXML
    void onAction_Seat(ActionEvent event) throws RemoteException { // проверок добавить
        try {
            category.getSelectionModel().select(searchCategoryName() - 1); // выбор места в зависимости от ряда и места
        } catch (NullPointerException e) { }
    }

    private int searchCategoryName() throws RemoteException {
        List<Seat> seatCategoryName = client.getSeatCategoryName(number.getValue(), row.getValue(), seat.getValue());
        return seatCategoryName.get(0).getCategory().getId();
    }

    @FXML
    void initialize() throws RemoteException {

        client = new BillingClient();

        seats = FXCollections.observableArrayList(client.getSeatList());
        categories = FXCollections.observableArrayList(client.getCategoryList());
        halls = FXCollections.observableArrayList(client.getHallList());

        try {
            for (int i = 0; i < halls.size(); i++) {
                number.getItems().add(halls.get(i).getId());
            }

            for (int i = 0; i < categories.size(); i++) {
                category.getItems().add(categories.get(i).getCategoryName());
            }
        } catch (NullPointerException e) { }
    }
}
