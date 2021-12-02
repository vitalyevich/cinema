package Client.Administration;

import Client.Model.Open;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AdministrationController extends Open {


    private final String STYLE_EXITED = "-fx-background-color: #3f3f3f; -fx-text-fill: white; -fx-background-radius: 1em";
    private final String STYLE_MOVED = "-fx-background-color: #bdbebd; -fx-text-fill: white; -fx-background-radius: 1em";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton buttonFilm;

    @FXML
    private JFXButton buttonStaff;

    @FXML
    private JFXButton buttonTicket;

    @FXML
    private JFXButton buttonBuffet;

    @FXML
    private JFXButton buttonStatistics;

    @FXML
    private JFXButton buttonExit;

    @FXML
    void OnClicked_Buffet(MouseEvent event) throws IOException {
        initFXMLWindow(buttonExit,"listBuffet.fxml","Буфет", 1, 1);
    }

    @FXML
    void OnClicked_Staff(MouseEvent event) throws IOException {
        initFXMLWindow(buttonExit,"staffControl.fxml","Работа с сотрудниками", 1,1);
    }

    @FXML
    void OnClicked_Exit(MouseEvent event) throws IOException {
        initFXML(buttonExit, "/Client/Authorization/authorization.fxml", 1);
    }

    @FXML
    void OnClicked_Film(MouseEvent event) throws IOException {
        initFXMLWindow(buttonFilm,"listFilm.fxml","Работа с фильмами", 1,1);
    }

    @FXML
    void OnClicked_Statistics(MouseEvent event) throws IOException {
        initFXMLWindow(buttonExit,"listStats.fxml","Статистика", 1, 1);
    }

    @FXML
    void OnClicked_Ticket(MouseEvent event) throws IOException {
        initFXMLWindow(buttonExit,"listTicket.fxml","Работа с билетами", 1, 1);
    }

    @FXML
    void OnExited_Buffet(MouseEvent event) {
        buttonBuffet.setStyle(STYLE_EXITED);
    }

    @FXML
    void OnExited_Staff(MouseEvent event) {
        buttonStaff.setStyle(STYLE_EXITED);
    }

    @FXML
    void OnExited_Exit(MouseEvent event) {
        buttonExit.setStyle(STYLE_EXITED);
    }

    @FXML
    void OnExited_Film(MouseEvent event) {
        buttonFilm.setStyle(STYLE_EXITED);
    }

    @FXML
    void OnExited_Statistics(MouseEvent event) {
        buttonStatistics.setStyle(STYLE_EXITED);
    }

    @FXML
    void OnExited_Ticket(MouseEvent event) {
        buttonTicket.setStyle(STYLE_EXITED);
    }

    @FXML
    void OnMoved_Buffet(MouseEvent event) {
        buttonBuffet.setStyle(STYLE_MOVED);
    }

    @FXML
    void OnMoved_Staff(MouseEvent event) {
        buttonStaff.setStyle(STYLE_MOVED);
    }

    @FXML
    void OnMoved_Exit(MouseEvent event) {
        buttonExit.setStyle(STYLE_MOVED);
    }

    @FXML
    void OnMoved_Film(MouseEvent event) {
        buttonFilm.setStyle(STYLE_MOVED);
    }

    @FXML
    void OnMoved_Statistics(MouseEvent event) {
        buttonStatistics.setStyle(STYLE_MOVED);
    }

    @FXML
    void OnMoved_Ticket(MouseEvent event) {
        buttonTicket.setStyle(STYLE_MOVED);
    }

    @FXML
    void initialize() {
    }
}