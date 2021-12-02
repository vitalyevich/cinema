package Client.Administration;

import Client.Model.Notification;
import Client.RMI.BillingClient;
import Server.Model.Film;
import Server.Model.Hall;
import Server.Model.Seance;
import com.jfoenix.controls.*;

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

public class SeanceController extends FilmController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton buttonSave;

    @FXML
    private JFXButton buttonAdd;

    @FXML
    private JFXButton buttonDelNumber;

    @FXML
    private JFXButton buttonDelDate;

    @FXML
    private JFXButton buttonDelName;

    @FXML
    private JFXComboBox<Integer> number;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTimePicker time;

    @FXML
    private JFXComboBox<String> hall;

    @FXML
    private JFXComboBox<String> film;

    @FXML
    private JFXComboBox<String> screen;

    private Hall hall1 = new Hall();
    private Film film1 = new Film();

    private void searchId() {
        hall1.setId(searchHallId(hall.getValue()));
        film1.setId(searchFilmId(film.getValue()));
    }

    @FXML
    void onClicked_Save(MouseEvent event) throws RemoteException {

        try {
            if (!number.getValue().equals("") || !date.getValue().equals("") || !time.getValue().equals("") ||
                    !hall.getValue().equals("") || !film.getValue().equals("") || !screen.getValue().equals("")) {
                notification.alert = new JFXAlert((Stage) buttonSave.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();

                    try {
                        searchId();
                        client.EditSeance(new Seance(number.getValue(),date.getValue(), time.getValue(), hall1, film1, screen.getValue()));

                        notification.getSuccess(buttonSave, notification.HEAD_EDIT, notification.SUCCESS_EDIT);
                        buttonSave.getScene().getWindow().hide();

                    } catch (RemoteException e) {
                        notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                    }
                });
            } else {
                notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR);
            }
        } catch (NullPointerException e) {
            notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR);
        }
    }

    private Notification notification = new Notification();

    @FXML
    void onClicked_Add(MouseEvent event) {

        try {
            if (!date.getValue().equals("") || !time.getValue().equals("") || !hall.getValue().equals("") ||
                    !film.getValue().equals("") || !screen.getValue().equals("")) {
                notification.alert = new JFXAlert((Stage) buttonAdd.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();

                    try {
                        searchId();
                        client.AddNewSeance(new Seance(date.getValue(), time.getValue(), hall1, film1, screen.getValue()));
                        notification.getSuccess(buttonAdd, notification.HEAD_ADD, notification.SUCCESS_ADD);
                        buttonAdd.getScene().getWindow().hide();

                    } catch (RemoteException e) {
                        notification.getError(buttonAdd, notification.HEAD_ADD, notification.ERROR_CONNECT);
                    } catch (SQLException e) {
                        notification.getError(buttonAdd, notification.HEAD_ADD, notification.ERROR);
                    }
                });
            } else {
                notification.getError(buttonAdd, notification.HEAD_ADD, notification.ERROR);
            }
        } catch (NullPointerException e) {
            notification.getError(buttonAdd, notification.HEAD_ADD, notification.ERROR);
        }
    }


    @FXML
    void onClicked_DelDate(MouseEvent event) throws RemoteException {

        try {
            if (!date.getValue().equals("") || !time.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) buttonDelDate.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteSeanceByDate(date.getValue(), time.getValue());

                    notification.getSuccess(buttonDelDate, notification.HEAD_DEL, notification.SUCCESS_DEL);

                } catch (RemoteException e) {
                    notification.getError(buttonDelDate, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(buttonDelDate, notification.HEAD_DEL,notification.ERROR);
        }

        buttonDelDate.getScene().getWindow().hide();
    }

    @FXML
    void onClicked_DelName(MouseEvent event) throws RemoteException {

        try {
            if (!film.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) buttonDelName.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    client.DeleteSeanceByName(searchFilmId(film.getValue()));

                    notification.getSuccess(buttonDelName, notification.HEAD_DEL, notification.SUCCESS_DEL);

                } catch (RemoteException e) {
                    notification.getError(buttonDelName, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(buttonDelName, notification.HEAD_DEL,notification.ERROR);
        }

        buttonDelName.getScene().getWindow().hide();
    }

    @FXML
    void onClicked_DelNumber(MouseEvent event) throws RemoteException {

        try {
            if (!number.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) buttonDelNumber.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteSeanceById(number.getValue());

                    notification.getSuccess(buttonDelNumber, notification.HEAD_DEL, notification.SUCCESS_DEL);

                    buttonDelNumber.getScene().getWindow().hide();

                } catch (RemoteException e) {
                    notification.getError(buttonDelNumber, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(buttonDelNumber, notification.HEAD_DEL,notification.ERROR);
        }
    }

    private int searchHallId(String name) {
        for (int i = 0; i < halls.size(); i++) {
            if (name.equals(halls.get(i).getHallName())) {
                return halls.get(i).getId();
            }
        }
        return 0;
    }

    private int searchFilmId(String name) {
        for (int i = 0; i < films.size(); i++) {
            if (name.equals(films.get(i).getFilmName())) {
                return films.get(i).getId();
            }
        }
        return 0;
    }

    @FXML
    void onAction_Number(ActionEvent event) throws RemoteException {

        List<Seance> seances = client.getSeance(number.getValue());
        date.setValue(seances.get(0).getShowDate());
        time.setValue(seances.get(0).getShowTime());
        hall.getSelectionModel().select(seances.get(0).getHall().getId() - 1); //плохой алгоритм
        film.getSelectionModel().select(seances.get(0).getFilm().getId() - 1);
        screen.getSelectionModel().select(searchScreenId(seances.get(0).getScreen()));
    }

    private int searchScreenId(String name) {
        if (name.equals("2D")) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @FXML
    void initialize() throws RemoteException {

        client = new BillingClient();

        seances = FXCollections.observableArrayList(client.getSeanceList());
        halls = FXCollections.observableArrayList(client.getHallList());
        films = FXCollections.observableArrayList(client.getFilmList());

        try {

            for (int i = 0; i < films.size(); i++) {
                film.getItems().add(films.get(i).getFilmName());
            }

            for (int i = 0; i < halls.size(); i++) {
                hall.getItems().add(halls.get(i).getHallName());
            }

            screen.getItems().add("2D");
            screen.getItems().add("3D");

        } catch (NullPointerException e) { }

        try {
            for (int i = 0; i < seances.size(); i++) {
                number.getItems().add(seances.get(i).getId());
            }
        } catch (NullPointerException e) { }
    }
}
