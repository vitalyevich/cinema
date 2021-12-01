package Client.Administration;

import Client.RMI.BillingClient;
import Server.Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

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

      /*  country.getSelectionModel().select(filmList.get(0).getCountry().getId() - 1);
        genre.getSelectionModel().select(filmList.get(0).getGenre().getId() - 1);*/
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
        fillComboCountry(country2);
        if (country1.getValue().equals("")) {
            country2.getItems().clear();
            country3.getItems().clear();
        }
    }

    @FXML
    void onAction_Country2(ActionEvent event) {
        fillComboCountry(country3);
        if (country2.getValue().equals("")) {
            country3.getItems().clear();
        }
    }


    @FXML
    void onAction_Genre(ActionEvent event) {
        fillComboGenre(genre1);
    }

    @FXML
    void onAction_Genre1(ActionEvent event) {
        fillComboGenre(genre2);
        if (genre1.getValue().equals("")) {
            genre2.getItems().clear();
            genre3.getItems().clear();
        }
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

    @FXML
    void onClicked_Add(MouseEvent event) throws RemoteException {

        int filmId = films.get(films.size() - 1).getId() + 1;

       // searchId();
        if (!name.getText().equals("") || !year.getText().equals("") || !time.getText().equals("") ||
        !description.getText().equals("") || !age.getText().equals("") || !rating.getText().equals("") ||
                !country.getValue().equals("") || genre.getValue().equals("")) {
            // проверка на ввод корректных данных
            client.AddNewFilm(new Film(name.getText(), Integer.parseInt(year.getText()), Integer.parseInt(time.getText()),
                    description.getText(), Integer.parseInt(age.getText()), Double.parseDouble(rating.getText())));



            genresNameId.setFilmId(filmId);
            countriesNameId.setFilmId(filmId);

            if (!country1.getValue().equals("")) {
                if (!country2.getValue().equals("")) {
                    if (!country3.getValue().equals("")) {
                        countriesNameId.setCountryId(Integer.parseInt(country3.getValue()));
                        client.AddNewFilmCountry(new CountriesName(countriesNameId));
                    }
                    else {
                        countriesNameId.setCountryId(Integer.parseInt(country2.getValue()));
                        client.AddNewFilmCountry(new CountriesName(countriesNameId));
                    }
                }
                else {
                    countriesNameId.setCountryId(Integer.parseInt(country1.getValue()));
                    client.AddNewFilmCountry(new CountriesName(countriesNameId));
                }
            }
            else {
                countriesNameId.setCountryId(Integer.parseInt(country.getValue()));
                client.AddNewFilmCountry(new CountriesName(countriesNameId));
            }

            if (!genre1.getValue().equals("")) {
                if (!genre2.getValue().equals("")) {
                    if (!genre3.getValue().equals("")) {
                        genresNameId.setGenreId(Integer.parseInt(genre3.getValue()));
                        client.AddNewFilmGenre(new GenresName(genresNameId));
                    }
                    else {
                        genresNameId.setGenreId(Integer.parseInt(genre2.getValue()));
                        client.AddNewFilmGenre(new GenresName(genresNameId));
                    }
                }
                else {
                    genresNameId.setGenreId(Integer.parseInt(genre1.getValue()));
                    client.AddNewFilmGenre(new GenresName(genresNameId));
                }
            }
            else {
                genresNameId.setGenreId(Integer.parseInt(genre.getValue()));
                client.AddNewFilmGenre(new GenresName(genresNameId));
            }


            // проверить заполнены ли жанры и страны!
            // жанры отдельно
        }
        else {

        }

    buttonAdd.getScene().getWindow().hide();
    }

    @FXML
    private JFXButton buttonSave;

    @FXML
    void onClicked_Save(MouseEvent event) {
/*
        try {
            client.EditFilm(new Film(number.getValue(),name.getText(), country1, Integer.parseInt(year.getText()), genre1,
                    Integer.parseInt(time.getText()), Long.parseLong(money.getText()),
                    description.getText(), Float.parseFloat(rating.getText())));
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
        buttonSave.getScene().getWindow().hide();
    }


    @FXML
    private JFXButton delNumber;

    @FXML
    private JFXButton delName;

    @FXML
    void onClicked_DelName(MouseEvent event) throws RemoteException {
        client.DeleteFilm(name.getText());
        delName.getScene().getWindow().hide();
    }

    @FXML
    void onClicked_DelNumber(MouseEvent event) throws RemoteException {
        client.DeleteFilmById(number.getValue());
        delNumber.getScene().getWindow().hide();
    }

    @FXML
    void initialize() throws RemoteException {

        client = new BillingClient();
        countries = FXCollections.observableArrayList(client.getCountryList());
        genres = FXCollections.observableArrayList(client.getGenreList());
        films = FXCollections.observableArrayList(client.getFilmList());

        for (int i = 0; i < countries.size(); i++) {
            country.getItems().add(countries.get(i).getCountryName());
        }

        for (int i = 0; i < genres.size(); i++) {
            genre.getItems().add(genres.get(i).getGenreName());
        }

       try {

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

    private int findGenreId(String name) {
        for (int i = 0; i < genres.size(); i++) {
            if (name.equals(genres.get(i).getGenreName())) {
                return genres.get(i).getId();
            }
        }
        return 0;
    }
}
