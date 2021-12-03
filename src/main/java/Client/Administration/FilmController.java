package Client.Administration;

import Client.Model.Notification;
import Client.Model.Open;
import Client.RMI.BillingClient;
import Server.Model.*;
import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FilmController extends Open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Film> tableFilm;

    @FXML
    private TableColumn<Film, Integer> numberColumn;

    @FXML
    private TableColumn<Film, Integer> ageColumn;

    @FXML
    private TableColumn<Film, String> nameColumn;

    @FXML
    private TableColumn<Film, Integer> yearColumn;

    @FXML
    private TableColumn<Film, String> timeColumn;

    @FXML
    private TableColumn<Film, Float> ratColumn;


    @FXML
    private JFXTextField nameCategory;

    @FXML
    private JFXComboBox<Integer> numberCategory;

    @FXML
    private JFXTextField nameGenre;

    @FXML
    private JFXComboBox<Integer> numberGenre;

    @FXML
    private JFXTextField nameCountry;

    @FXML
    private JFXComboBox<Integer> numberCountry;

    @FXML
    private JFXTextField searchFilm;

    @FXML
    private JFXButton addFilm;

    @FXML
    private JFXButton editFilm;

    @FXML
    private JFXButton delFilm;

    @FXML
    private JFXButton clearFilm;

    @FXML
    private TableView<Seance> seanceTable;

    @FXML
    private TableColumn<Seance, Integer> idColumn;

    @FXML
    private TableColumn<Seance, String> dateColumn;

    @FXML
    private TableColumn<Seance, String> durationColumn;

    @FXML
    private TableColumn<Seance, String> nameHallColumn;

    @FXML
    private TableColumn<Seance, String> nameFilmColumn;

    @FXML
    private TableColumn<Seance, String> screenColumn;

    @FXML
    private JFXButton addSeance;

    @FXML
    private JFXButton editSeance;

    @FXML
    private JFXButton delSeance;

    @FXML
    private JFXButton clearSeance;

    @FXML
    private JFXDatePicker sortDate;

    @FXML
    private TableView<Hall> hallTable;

    @FXML
    private TableColumn<Hall, Integer> numColumn;

    @FXML
    private TableColumn<Hall, String> hallColumn;

    @FXML
    private TableColumn<Hall, Integer> seatsColumn;

    @FXML
    private TableColumn<Hall, Integer> rowsColumn;

    @FXML
    private JFXButton buttonView;

    @FXML
    private JFXButton addHall;

    @FXML
    private JFXButton editHall;

    @FXML
    private JFXButton delHall;

    @FXML
    private JFXButton clearHall;

    @FXML
    private TableView<Seat> seatTable;

    @FXML
    private TableColumn<Seat, String> namHallColumn;

    @FXML
    private TableColumn<Seat, Integer> rowColumn;

    @FXML
    private TableColumn<Seat, Integer> seatColumn;

    @FXML
    private TableColumn<Seat, String> catColumn;

    @FXML
    private JFXTextField searchSeat;

    @FXML
    private JFXButton addSeat;

    @FXML
    private JFXButton editSeat;

    @FXML
    private JFXButton delSeat;

    @FXML
    private JFXButton clearSeat;

    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXTextField nameHall;

    @FXML
    private JFXComboBox<Integer> numberHall;

    @FXML
    void onAction_searchFilm(ActionEvent event) {

    }

    @FXML
    void onAction_searchSession(ActionEvent event) {

    }

    @FXML
    void onAction_sortDate(ActionEvent event) {

        try {
            if (!sortDate.getValue().equals("")) {

                tempSeance = FXCollections.observableArrayList();

                for (int i = 0; i < seances.size(); i++) {
                    if (seances.get(i).getDate().contains(sortDate.getValue()
                            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).trim())) {
                        tempSeance.add(seances.get(i));
                    }
                }
                fillingColumnTableSeance(tempSeance);
            }
        }
        catch (NullPointerException e) {
            fillingColumnTableSeance(seances);
        }
    }

    @FXML
    void onAction_numberHall(ActionEvent event) throws RemoteException {

        List<Hall> seatList = client.getHall(numberHall.getValue());
        nameHall.setText(seatList.get(0).getHallName());
    }


    @FXML
    void onMouseClicked_View(MouseEvent event) throws IOException {
        initFXMLWindow(buttonView, "screen.fxml","Представление залла",0,1);
    }

    @FXML
    void onMouseClicked_addFilm(MouseEvent event) throws IOException {
        initFXMLWindow(addFilm, "addFilm.fxml","Добавление фильма", 0,0);
    }

    @FXML
    void onMouseClicked_addHall(MouseEvent event) {
        if(!nameHall.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) addHall.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    Hall hall = new Hall();
                    hall.setHallName(nameHall.getText());
                    hall.setRowTotal(0);
                    hall.setSeatTotal(0);

                    client.AddNewHall(hall);

                    notification.getSuccess(addHall, notification.HEAD_ADD,notification.SUCCESS_ADD);

                    clearText();
                    fillingTableHall();

                } catch (RemoteException e) {
                    notification.getError(addHall, notification.HEAD_ADD,notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(addHall, notification.HEAD_ADD,notification.ERROR);
                }
            });
        }
        else {
            notification.getError(addHall, notification.HEAD_ADD,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_addSeat(MouseEvent event) throws IOException {
        initFXMLWindow(addSeat, "addSeat.fxml","Добавление места", 0,0);
    }

    @FXML
    void onMouseClicked_addSeance(MouseEvent event) throws IOException {
        initFXMLWindow(addSeance, "addSeance.fxml","Добавление сеанса", 0,0);
    }

    @FXML
    void onMouseClicked_clearFilm(MouseEvent event) throws RemoteException {
        notification.alert = new JFXAlert((Stage) clearFilm.getScene().getWindow());
        notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableFilm();
                notification.getSuccess(clearFilm, notification.HEAD_DEL,notification.SUCCESS_DEL);

                clearText();
                fillingTableFilm();

            } catch (RemoteException e) {
                notification.getError(clearFilm, notification.HEAD_DEL,notification.ERROR_CONNECT);
            }
        });
    }

    @FXML
    void onMouseClicked_clearHall(MouseEvent event) {
        notification.alert = new JFXAlert((Stage) clearHall.getScene().getWindow());
        notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableHall();
                notification.getSuccess(clearHall, notification.HEAD_DEL,notification.SUCCESS_DEL);

                clearText();
                fillingTableHall();

            } catch (RemoteException e) {
                notification.getError(clearHall, notification.HEAD_DEL,notification.ERROR_CONNECT);
            }
        });
    }

    @FXML
    void onMouseClicked_clearSeat(MouseEvent event) throws RemoteException {
        notification.alert = new JFXAlert((Stage) clearSeat.getScene().getWindow());
        notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableSeat();

                notification.getSuccess(clearSeat,notification.HEAD_DEL, notification.SUCCESS_DEL);

                clearText();
                fillingTableSeat();

            } catch (RemoteException e) {
                notification.getError(clearSeat, notification.HEAD_DEL, notification.ERROR_CONNECT);
            }
        });
    }

    @FXML
    void onMouseClicked_clearSeance(MouseEvent event) throws RemoteException {
        notification.alert = new JFXAlert((Stage) clearSeance.getScene().getWindow());
        notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableSeance();

                notification.getSuccess(clearSeance,notification.HEAD_DEL, notification.SUCCESS_DEL);

                clearText();
                fillingTableSeance();

            } catch (RemoteException e) {
                notification.getError(clearSeance, notification.HEAD_DEL, notification.ERROR_CONNECT);
            }
        });
    }

    @FXML
    void onMouseClicked_delFilm(MouseEvent event) throws IOException {
        initFXMLWindow(delFilm, "delFilm.fxml","Удаление фильма", 0,0);
    }

    @FXML
    void onMouseClicked_delHall(MouseEvent event) throws RemoteException {
        try {
            if (!numberHall.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) delHall.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteHall(numberHall.getValue());
                    notification.getSuccess(delHall, notification.HEAD_DEL, notification.SUCCESS_DEL);

                    clearText();
                    fillingTableHall();

                } catch (RemoteException e) {
                    notification.getError(delHall, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(delHall, notification.HEAD_DEL,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_delSeat(MouseEvent event) throws IOException {
        initFXMLWindow(delSeat, "delSeat.fxml","Удаление места", 0,0);
    }

    @FXML
    void onMouseClicked_delSeance(MouseEvent event) throws IOException {
        initFXMLWindow(delSeance, "delSeance.fxml","Удаление сеанса", 0,0);
    }

    @FXML
    void onMouseClicked_editFilm(MouseEvent event) throws IOException {
        initFXMLWindow(editFilm, "editFilm.fxml","Редактирование фильма", 0,0);
    }

    @FXML
    void onMouseClicked_editHall(MouseEvent event) throws RemoteException {
        if(!nameHall.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) editHall.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.EditHall(new Hall(numberHall.getValue(),nameHall.getText()));
                    notification.getSuccess(editHall, notification.HEAD_EDIT,notification.SUCCESS_EDIT);

                    fillingTableHall();
                    clearText();

                } catch (RemoteException e) {
                    notification.getError(editHall, notification.HEAD_EDIT,notification.ERROR_CONNECT);
                }
            });
        }
        else {
            notification.getError(editHall, notification.HEAD_EDIT,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_editSeat(MouseEvent event) throws IOException {
        initFXMLWindow(editSeat, "editSeat.fxml","Редактирование места", 0,0);
    }

    @FXML
    void onMouseClicked_editSeance(MouseEvent event) throws IOException {
        initFXMLWindow(editSeance, "editSeance.fxml","Редактирование сеанса", 0,0);
    }

    @FXML
    private TableView<Category> categoryTable;

    @FXML
    private TableColumn<Category, Integer> numericColumn;

    @FXML
    private TableColumn<Category, String> categoryColumn;

    @FXML
    private TableView<Genre> genreTable;

    @FXML
    private TableColumn<Genre, Integer> nmrColumn;

    @FXML
    private TableColumn<Genre, String> genColumn;

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, Integer> nmrcColumn;

    @FXML
    private TableColumn<Country, String> nameCountryColumn;

    @FXML
    private JFXButton addCountry;

    @FXML
    private JFXButton editCountry;

    @FXML
    private JFXButton delCountry;

    @FXML
    private JFXButton clearCountry;

    private Notification notification = new Notification();;

    @FXML
    void onMouseClicked_addCountry(MouseEvent event) {

        if(!nameCountry.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) addCountry.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.AddNewCountry(new Country(nameCountry.getText()));

                    notification.getSuccess(addCountry, notification.HEAD_ADD,notification.SUCCESS_ADD);

                    clearText();
                    fillingTableCountry();

                } catch (RemoteException e) {
                    notification.getError(addCountry, notification.HEAD_ADD,notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(addCountry, notification.HEAD_ADD,notification.ERROR);
                }
            });
        }
        else {
            notification.getError(addCountry, notification.HEAD_ADD,notification.ERROR);
        }

    }

    private void clearText() {
        nameCountry.setText("");
        nameCategory.setText("");
        nameGenre.setText("");
        nameHall.setText("");
    }

    @FXML
    void onMouseClicked_delCountry(MouseEvent event) {

        try {
            if (!numberCountry.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) delCountry.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteCountry(numberCountry.getValue());

                    notification.getSuccess(delCountry, notification.HEAD_DEL, notification.SUCCESS_DEL);

                    clearText();
                    fillingTableCountry();

                } catch (RemoteException e) {
                    notification.getError(delCountry, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

    } catch (NullPointerException e) {
            notification.getError(delCountry, notification.HEAD_DEL,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_editCountry(MouseEvent event) {

        if(!nameCountry.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) editCountry.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.EditCountry(new Country(numberCountry.getValue(), nameCountry.getText()));

                    notification.getSuccess(editCountry, notification.HEAD_EDIT,notification.SUCCESS_EDIT);

                    fillingTableCountry();
                    clearText();

                } catch (RemoteException e) {
                    notification.getError(editCountry, notification.HEAD_EDIT,notification.ERROR_CONNECT);
                }
            });
        }
        else {
            notification.getError(editCountry, notification.HEAD_EDIT,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_clearCountry(MouseEvent event) {

            notification.alert = new JFXAlert((Stage) clearCountry.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.TruncateTableCountry();

                    notification.getSuccess(clearCountry, notification.HEAD_DEL,notification.SUCCESS_DEL);

                    clearText();
                    fillingTableCountry();

                } catch (RemoteException e) {
                    notification.getError(clearCountry, notification.HEAD_DEL,notification.ERROR_CONNECT);
                }
            });
    }

    @FXML
    void onAction_numberCountry(ActionEvent event) throws RemoteException {
        List<Country> country = client.getCountry(numberCountry.getValue());
        nameCountry.setText(country.get(0).getCountryName());
    }

    @FXML
    private JFXButton addGenre;

    @FXML
    private JFXButton editGenre;

    @FXML
    private JFXButton delGenre;

    @FXML
    private JFXButton clearGenre;

    @FXML
    void onAction_numberGenre(ActionEvent event) throws RemoteException {
        List<Genre> genres = client.getGenre(numberGenre.getValue());
        nameGenre.setText(genres.get(0).getGenreName());
    }

    @FXML
    void onMouseClicked_addGenre(MouseEvent event) {

        if(!nameGenre.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) addGenre.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.AddNewGenre(new Genre(nameGenre.getText()));

                    notification.getSuccess(addGenre, notification.HEAD_ADD,notification.SUCCESS_ADD);

                    clearText();
                    fillingTableGenre();

                } catch (RemoteException e) {
                    notification.getError(addGenre, notification.HEAD_ADD,notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(addGenre, notification.HEAD_ADD,notification.ERROR);
                }
            });
        }
        else {
            notification.getError(addGenre, notification.HEAD_ADD,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_clearGenre(MouseEvent event) throws RemoteException {
        notification.alert = new JFXAlert((Stage) clearGenre.getScene().getWindow());
        notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableGenre();
                notification.getSuccess(clearGenre, notification.HEAD_DEL,notification.SUCCESS_DEL);

                clearText();
                fillingTableGenre();

            } catch (RemoteException e) {
                notification.getError(clearGenre, notification.HEAD_DEL,notification.ERROR_CONNECT);
            }
        });
    }

    @FXML
    void onMouseClicked_delGenre(MouseEvent event) throws RemoteException {
        try {
            if (!numberGenre.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) delGenre.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteGenre(numberGenre.getValue());

                    notification.getSuccess(delGenre, notification.HEAD_DEL, notification.SUCCESS_DEL);

                    clearText();
                    fillingTableGenre();

                } catch (RemoteException e) {
                    notification.getError(delGenre, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(delGenre, notification.HEAD_DEL,notification.ERROR);
        }
    }


    @FXML
    void onMouseClicked_editGenre(MouseEvent event) throws RemoteException {

        if(!nameGenre.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) editGenre.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.EditGenre(new Genre(numberGenre.getValue(), nameGenre.getText()));

                    notification.getSuccess(editGenre, notification.HEAD_EDIT,notification.SUCCESS_EDIT);

                    clearText();
                    fillingTableGenre();

                } catch (RemoteException e) {
                    notification.getError(editGenre, notification.HEAD_EDIT,notification.ERROR_CONNECT);
                }
            });
        }
        else {
            notification.getError(editGenre, notification.HEAD_EDIT,notification.ERROR);
        }
    }

    @FXML
    private JFXButton addCategory;

    @FXML
    private JFXButton editCategory;

    @FXML
    private JFXButton delCategory;

    @FXML
    private JFXButton clearCategory;

    @FXML
    void onAction_numberCategory(ActionEvent event) throws RemoteException {
        List<Category> categories = client.getCategory(numberCategory.getValue());
        nameCategory.setText(categories.get(0).getCategoryName());
    }

    @FXML
    void onMouseClicked_addCategory(MouseEvent event) {
        if(!nameCategory.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) addCategory.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    client.AddNewCategory(new Category(nameCategory.getText()));

                    notification.getSuccess(addCategory, notification.HEAD_ADD,notification.SUCCESS_ADD);

                    clearText();
                    fillingTableCategory();

                } catch (RemoteException e) {
                    notification.getError(addCategory, notification.HEAD_ADD,notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(addCategory, notification.HEAD_ADD,notification.ERROR);
                }
            });
        }
        else {
            notification.getError(addCategory, notification.HEAD_ADD,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_clearCategory(MouseEvent event) throws RemoteException {
        notification.alert = new JFXAlert((Stage) clearCategory.getScene().getWindow());
        notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableCategory();

                notification.getSuccess(clearCategory, notification.HEAD_DEL,notification.SUCCESS_DEL);

                clearText();
                fillingTableCategory();

            } catch (RemoteException e) {
                notification.getError(clearCategory, notification.HEAD_DEL,notification.ERROR_CONNECT);
            }
        });
    }

    @FXML
    void onMouseClicked_delCategory(MouseEvent event) {
        try {
            if (!numberCategory.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) delCategory.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteCategory(numberCategory.getValue());
                    notification.getSuccess(delCategory, notification.HEAD_DEL, notification.SUCCESS_DEL);

                    clearText();
                    fillingTableCategory();

                } catch (RemoteException e) {
                    notification.getError(delCategory, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(delCategory, notification.HEAD_DEL,notification.ERROR);
        }
    }

    @FXML
    void onMouseClicked_editCategory(MouseEvent event) {
        if(!nameCategory.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) editCategory.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.EditCategory(new Category(numberCategory.getValue(), nameCategory.getText()));

                    notification.getSuccess(editCategory, notification.HEAD_EDIT,notification.SUCCESS_EDIT);

                    clearText();
                    fillingTableCategory();

                } catch (RemoteException e) {
                    notification.getError(editCategory, notification.HEAD_EDIT,notification.ERROR_CONNECT);
                }
            });
        }
        else {
            notification.getError(editCategory, notification.HEAD_EDIT,notification.ERROR);
        }
    }

    protected ObservableList<Film> films = FXCollections.observableArrayList();
    private ObservableList<Film> tempData = FXCollections.observableArrayList();
    protected ObservableList<Seance> seances = FXCollections.observableArrayList();
    private ObservableList<Seat> tempSeat = FXCollections.observableArrayList();
    private ObservableList<Seance> tempSeance = FXCollections.observableArrayList();
    protected ObservableList<Hall> halls = FXCollections.observableArrayList();
    protected ObservableList<Seat> seats = FXCollections.observableArrayList();
    protected ObservableList<Category> categories = FXCollections.observableArrayList();
    protected ObservableList<Genre> genres = FXCollections.observableArrayList();
    protected ObservableList<Country> countries = FXCollections.observableArrayList();
    protected BillingClient client = new BillingClient();

    @FXML
    void initialize() throws IOException {
        initMenu(anchor);

        fillingTableFilm();

        fillingTableSeat();


        new Thread( ()-> {
            try {
                fillingTableHall();
                fillingTableCountry();
                fillingTableGenre();
                fillingTableCategory();
                fillingTableSeance();
                fillingTableSeat();
                fillingTableFilm();
                fillingTableSeance();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }).start();

        searchFilm.textProperty().addListener((obs, oldText, newText) -> {
            if (!searchFilm.getText().isEmpty()) {

                tempData = FXCollections.observableArrayList();

                for (int i = 0; i < films.size(); i++)
                {
                    if (films.get(i).getFilmName().toLowerCase().contains(searchFilm.getText().toLowerCase().trim()))
                    {
                        tempData.add(films.get(i));
                    }
                }
                fillingColumnTableFilm(tempData);
            }
            else {
                fillingColumnTableFilm(films);
            }
        });

        searchSeat.textProperty().addListener((obs, oldText, newText) -> {
            if (!searchSeat.getText().isEmpty()) {

                tempSeat = FXCollections.observableArrayList();

                for (int i = 0; i < seats.size(); i++)
                {
                    if (seats.get(i).getHall().getId().equals(Integer.parseInt(searchSeat.getText().trim())))
                    {
                        tempSeat.add(seats.get(i));
                    }
                }
                fillingColumnTableSeat(tempSeat);
            }
            else {
                fillingColumnTableSeat(seats);
            }
        });
    }

    protected void fillingTableFilm() throws RemoteException {

        films = FXCollections.observableArrayList(client.getFilmList());

        fillingColumnTableFilm(films);
    }

    private void fillingColumnTableFilm(ObservableList<Film> list) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("filmName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("showTime"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("ageNum"));
        ratColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableFilm.setItems(list);
    }

    private void fillingTableSeance() throws RemoteException {

        seances = FXCollections.observableArrayList(client.getSeanceList());

        fillingColumnTableSeance(seances);
    }

    private void fillingColumnTableSeance(ObservableList<Seance> observableList) { // потоки добавить если много записей

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("showTime"));
        nameHallColumn.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        nameFilmColumn.setCellValueFactory(new PropertyValueFactory<>("filmName"));
        screenColumn.setCellValueFactory(new PropertyValueFactory<>("screen"));
        seanceTable.setItems(observableList);
    }

    private void fillingTableHall() throws RemoteException {

        halls = FXCollections.observableArrayList(client.getHallList());

        numColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        hallColumn.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seatTotal"));
        rowsColumn.setCellValueFactory(new PropertyValueFactory<>("rowTotal"));
        hallTable.setItems(halls);

        numberHall.getItems().clear();

        for (int i = 0; i < halls.size(); i++) {
            numberHall.getItems().add(halls.get(i).getId());
        }
    }

    protected void fillingTableSeat() throws RemoteException {

        seats = FXCollections.observableArrayList(client.getSeatList());
        fillingColumnTableSeat(seats);
    }

    private void  fillingColumnTableSeat(ObservableList<Seat> seatList) {
        namHallColumn.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        rowColumn.setCellValueFactory(new PropertyValueFactory<>("rowNum"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seatNum"));
        catColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        seatTable.setItems(seatList);
    }


    private void fillingTableCategory() throws RemoteException {

        categories = FXCollections.observableArrayList(client.getCategoryList());

        numericColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        categoryTable.setItems(categories);

        numberCategory.getItems().clear();

        for (int i = 0; i < categories.size(); i++) {
            numberCategory.getItems().add(categories.get(i).getId());
        }
    }

    private void fillingTableGenre() {
        Platform.runLater(() -> {
            try {
                genres = FXCollections.observableArrayList(client.getGenreList());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            nmrColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            genColumn.setCellValueFactory(new PropertyValueFactory<>("genreName"));
            genreTable.setItems(genres);

            numberGenre.getItems().clear();

            for (int i = 0; i < genres.size(); i++) {
                numberGenre.getItems().add(genres.get(i).getId());
            }
        });
    }

    private void fillingTableCountry() throws RemoteException {
        Platform.runLater(() -> {
            try {
                countries = FXCollections.observableArrayList(client.getCountryList());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            nmrcColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCountryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
            countryTable.setItems(countries);

            numberCountry.getItems().clear();

            for (int i = 0; i < countries.size(); i++) {
                numberCountry.getItems().add(countries.get(i).getId());
            }
        });
    }


}
