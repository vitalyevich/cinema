package coursework.controllers;

import coursework.operations.Notification;
import coursework.rmi.BillingClient;
import coursework.models.*;
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

public class OperationFilmController extends FilmController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXComboBox<Integer> number;

    @FXML
    private JFXButton buttonAdd;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXComboBox<String> country;

    @FXML
    private JFXComboBox<String> country1;

    @FXML
    private JFXComboBox<String> country2;

    @FXML
    private JFXComboBox<String> country3;

    @FXML
    private JFXTextField year;

    @FXML
    private JFXComboBox<String> genre;

    @FXML
    private JFXComboBox<String> genre1;

    @FXML
    private JFXComboBox<String> genre2;

    @FXML
    private JFXComboBox<String> genre3;

    @FXML
    private JFXTextField time;

    @FXML
    private JFXTextField age;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField rating;


    @FXML
    void onAction_Number(ActionEvent event) throws RemoteException {
        country.getItems().clear();
        genre.getItems().clear();
        fillingColumnChoice();

        List<Film> filmList = client.getFilm(Integer.parseInt(String.valueOf(number.getValue())));
        name.setText(filmList.get(0).getFilmName());
        year.setText(filmList.get(0).getReleaseDate() + "");
        time.setText(filmList.get(0).getShowTime() + "");
        age.setText(filmList.get(0).getAgeNum()+ "");
        description.setText(filmList.get(0).getFilmDescription());
        rating.setText(filmList.get(0).getRating() + "");

        List<GenresName> genresNames = client.getFilmGenre(Integer.parseInt(String.valueOf(number.getValue())));
        List<CountriesName> countriesNames = client.getFilmCountry(Integer.parseInt(String.valueOf(number.getValue())));

        for (int i = 0; i < genresNames.size(); i++) {
            if(i == 0) {
                genre.getSelectionModel().select(genresNames.get(i).getId().getGenreId() - 1);
            }
            else if(i == 1) {
                genre1.getSelectionModel().select(genresNames.get(i).getId().getGenreId());
            }
            else if(i == 2) {
                genre2.getSelectionModel().select(genresNames.get(i).getId().getGenreId());
            }
            else if(i == 3) {
                genre3.getSelectionModel().select(genresNames.get(i).getId().getGenreId());
            }
        }

        for (int i = 0; i < countriesNames.size(); i++) {
            if(i == 0) {
                country.getSelectionModel().select(countriesNames.get(i).getId().getCountryId() - 1);
            }
            else if(i == 1) {
                country1.getSelectionModel().select(countriesNames.get(i).getId().getCountryId());
            }
            else if(i == 2) {
                country2.getSelectionModel().select(countriesNames.get(i).getId().getCountryId());
            }
            else if(i == 3) {
                country3.getSelectionModel().select(countriesNames.get(i).getId().getCountryId());
            }
        }

    }


    private void fillComboCountry(JFXComboBox<String> str) {

        str.getItems().clear();

        str.getItems().add("");

        for (int i = 0; i < countries.size(); i++) {
            str.getItems().add(countries.get(i).getCountryName());
        }
    }

    private void fillComboGenre(JFXComboBox<String> str) {

        str.getItems().clear();

        str.getItems().add("");

        for (int i = 0; i < genres.size(); i++) {
            str.getItems().add(genres.get(i).getGenreName());
        }
    }

    @FXML
    void onAction_Country(ActionEvent event) {
        fillComboCountry(country1);
    }

    @FXML
    void onAction_Country1(ActionEvent event) {
        try {
            fillComboCountry(country2);
            if (country1.getValue().equals("")) {
                country2.getItems().clear();
                country3.getItems().clear();
            }
        } catch (NullPointerException e) { }
    }

    @FXML
    void onAction_Country2(ActionEvent event) {
        try {
            fillComboCountry(country3);
            if (country2.getValue().equals("")) {
                country3.getItems().clear();
            }
        } catch (NullPointerException e) { }
    }


    @FXML
    void onAction_Genre(ActionEvent event) {
        fillComboGenre(genre1);
    }

    @FXML
    void onAction_Genre1(ActionEvent event) {
        try {
            fillComboGenre(genre2);
            if (genre1.getValue().equals("")) {
                genre2.getItems().clear();
                genre3.getItems().clear();
            }
        } catch (NullPointerException e) { }
    }

    @FXML
    void onAction_Genre2(ActionEvent event) {
        fillComboGenre(genre3);
        if (genre2.getValue().equals("")) {
            genre3.getItems().clear();
        }
    }

    private GenresNameId genresNameId = new GenresNameId();
    private CountriesNameId countriesNameId = new CountriesNameId();

    private int searchIdCountry(String name) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getCountryName().equals(name)) {
                return i+1;
            }
        }
        return 0;
    }

    private int searchIdGenre(String name) {
        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i).getGenreName().equals(name)) {
                return i+1;
            }
        }
        return 0;
    }

    @FXML
    void onClicked_Add(MouseEvent event) {
        try {
            if (name.getText().equals("") || year.getText().equals("") || time.getText().equals("") ||
                    description.getText().equals("") || age.getText().equals("") || rating.getText().equals("") ||
                    country.getValue().equals("") || genre.getValue().equals("")) {

                throw new NullPointerException();

            }
                notification.alert = new JFXAlert((Stage) buttonAdd.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();

                    try {
                        int filmId = client.AddNewFilm(new Film(name.getText(), Integer.parseInt(year.getText()), Integer.parseInt(time.getText()),
                                description.getText(), Integer.parseInt(age.getText()), Double.parseDouble(rating.getText())));

                        try {
                            countriesNameId.setCountryId(searchIdCountry(country.getValue()));
                            countriesNameId.setFilmId(filmId);
                            client.AddNewFilmCountry(new CountriesName(countriesNameId));

                            country1.getValue().equals("");
                            countriesNameId.setCountryId(searchIdCountry(country1.getValue()));
                            countriesNameId.setFilmId(filmId);
                            client.AddNewFilmCountry(new CountriesName(countriesNameId));

                            country2.getValue().equals("");
                            countriesNameId.setCountryId(searchIdCountry(country2.getValue()));
                            countriesNameId.setFilmId(filmId);
                            client.AddNewFilmCountry(new CountriesName(countriesNameId));

                            country3.getValue().equals("");
                            countriesNameId.setCountryId(searchIdCountry(country3.getValue()));
                            countriesNameId.setFilmId(filmId);
                            client.AddNewFilmCountry(new CountriesName(countriesNameId));

                        } catch (NullPointerException e) { }

                        try {
                            genresNameId.setGenreId(searchIdGenre(genre.getValue()));
                            genresNameId.setFilmId(filmId);
                            client.AddNewFilmGenre(new GenresName(genresNameId));

                            genre1.getValue().equals("");
                            genresNameId.setGenreId(searchIdGenre(genre1.getValue()));
                            genresNameId.setFilmId(filmId);
                            client.AddNewFilmGenre(new GenresName(genresNameId));

                            genre2.getValue().equals("");
                            genresNameId.setGenreId(searchIdGenre(genre2.getValue()));
                            genresNameId.setFilmId(filmId);
                            client.AddNewFilmGenre(new GenresName(genresNameId));

                            genre3.getValue().equals("");
                            genresNameId.setGenreId(searchIdGenre(genre3.getValue()));
                            genresNameId.setFilmId(filmId);
                            client.AddNewFilmGenre(new GenresName(genresNameId));
                        } catch (NullPointerException e) { }
                        finally {
                            notification.getSuccess(buttonAdd, notification.HEAD_ADD, notification.SUCCESS_ADD);
                            buttonAdd.getScene().getWindow().hide();
                        }
                    } catch (RemoteException e) {
                        notification.getError(buttonAdd, notification.HEAD_ADD, notification.ERROR_CONNECT);
                    } catch (SQLException e) {
                        notification.getError(buttonAdd, notification.HEAD_ADD, notification.ERROR);
                    }
                });
        } catch (NullPointerException e) {
            notification.getError(buttonAdd, notification.HEAD_ADD, notification.ERROR);
        }
    }

    @FXML
    private JFXButton buttonSave;

    @FXML
    void onClicked_Save(MouseEvent event) {
        try {
            if (name.getText().equals("") || year.getText().equals("") || time.getText().equals("") ||
                    description.getText().equals("") || age.getText().equals("") || rating.getText().equals("") ||
                    country.getValue().equals("") || genre.getValue().equals("")) {

                throw new NullPointerException();

            }
            notification.alert = new JFXAlert((Stage) buttonSave.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    int filmId = number.getValue();

                    client.EditFilm(new Film(number.getValue(),name.getText(), Integer.parseInt(year.getText()), Integer.parseInt(time.getText()),
                            description.getText(), Integer.parseInt(age.getText()), Double.parseDouble(rating.getText())));

                    client.EditFilmCountry(filmId);
                    client.EditFilmGenre(filmId);

                    try {
                        countriesNameId.setCountryId(searchIdCountry(country.getValue()));
                        countriesNameId.setFilmId(filmId);
                        client.AddNewFilmCountry(new CountriesName(countriesNameId));

                        country1.getValue().equals("");
                        countriesNameId.setCountryId(searchIdCountry(country1.getValue()));
                        countriesNameId.setFilmId(filmId);
                        client.AddNewFilmCountry(new CountriesName(countriesNameId));

                        country2.getValue().equals("");
                        countriesNameId.setCountryId(searchIdCountry(country2.getValue()));
                        countriesNameId.setFilmId(filmId);
                        client.AddNewFilmCountry(new CountriesName(countriesNameId));

                        country3.getValue().equals("");
                        countriesNameId.setCountryId(searchIdCountry(country3.getValue()));
                        countriesNameId.setFilmId(filmId);
                        client.AddNewFilmCountry(new CountriesName(countriesNameId));

                    } catch (NullPointerException e) { }

                    try {
                        genresNameId.setGenreId(searchIdGenre(genre.getValue()));
                        genresNameId.setFilmId(filmId);
                        client.AddNewFilmGenre(new GenresName(genresNameId));

                        genre1.getValue().equals("");
                        genresNameId.setGenreId(searchIdGenre(genre1.getValue()));
                        genresNameId.setFilmId(filmId);
                        client.AddNewFilmGenre(new GenresName(genresNameId));

                        genre2.getValue().equals("");
                        genresNameId.setGenreId(searchIdGenre(genre2.getValue()));
                        genresNameId.setFilmId(filmId);
                        client.AddNewFilmGenre(new GenresName(genresNameId));

                        genre3.getValue().equals("");
                        genresNameId.setGenreId(searchIdGenre(genre3.getValue()));
                        genresNameId.setFilmId(filmId);
                        client.AddNewFilmGenre(new GenresName(genresNameId));
                    } catch (NullPointerException e) { }
                    finally {
                        notification.getSuccess(buttonSave, notification.HEAD_EDIT, notification.SUCCESS_EDIT);
                        buttonSave.getScene().getWindow().hide();
                    }

                } catch (RemoteException e) {
                    notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(buttonSave, notification.HEAD_EDIT, notification.ERROR);
        }
    }


    @FXML
    private JFXButton delNumber;

    @FXML
    private JFXButton delName;

    @FXML
    void onClicked_DelName(MouseEvent event) throws RemoteException {
        if (!name.getText().equals("")) {
            notification.alert = new JFXAlert((Stage) delName.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    client.DeleteFilm(name.getText());

                    notification.getSuccess(delName, notification.HEAD_DEL, notification.SUCCESS_DEL);

                } catch (RemoteException e) {
                    notification.getError(delName, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });
        } else {
            notification.getError(delName, notification.HEAD_DEL, notification.ERROR);
        }

        delName.getScene().getWindow().hide();
    }

    private Notification notification = new Notification();

    @FXML
    void onClicked_DelNumber(MouseEvent event) throws RemoteException {
        try {
            if (!number.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) delNumber.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    client.DeleteFilmById(number.getValue());

                    notification.getSuccess(delNumber, notification.HEAD_DEL, notification.SUCCESS_DEL);

                } catch (RemoteException e) {
                    notification.getError(delNumber, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(delNumber, notification.HEAD_DEL,notification.ERROR);
        }

        delNumber.getScene().getWindow().hide();
    }

    @FXML
    void initialize() throws RemoteException {

        client = new BillingClient();
        countries = FXCollections.observableArrayList(client.getCountryList());
        genres = FXCollections.observableArrayList(client.getGenreList());
        films = FXCollections.observableArrayList(client.getFilmList());


       try {

            fillingColumnChoice();

            for (int i = 0; i < films.size(); i++) {
                number.getItems().add(films.get(i).getId());
            }
        } catch (NullPointerException e) { }

    }

    private int findCountryId(String name) {
        for (int i = 0; i < countries.size(); i++) {
            if (name.equals(countries.get(i).getCountryName())) {
                return countries.get(i).getId();
            }
        }
       return 0;
    }

    private void fillingColumnChoice() {
        try {
            for (int i = 0; i < countries.size(); i++) {
                country.getItems().add(countries.get(i).getCountryName());
            }

            for (int i = 0; i < genres.size(); i++) {
                genre.getItems().add(genres.get(i).getGenreName());
            }
        } catch (NullPointerException e) { }
    }

    private int findGenreId(String name) {
        for (int i = 0; i < genres.size(); i++) {
            if (name.equals(genres.get(i).getGenreName())) {
                return genres.get(i).getId();
            }
        }
        return 0;
    }
}
