package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import coursework.models.Hall;
import coursework.models.Seat;
import coursework.operations.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScreenController extends FilmController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox boxIdRight;

    @FXML
    private VBox boxIdLeft;

    @FXML
    private VBox boxSeat;

    @FXML
    private Label label;

    private List<Seat> seatList;
    private List<Hall> hallList;
    private Session session = Session.getInstance();

    @FXML
    private JFXButton buttonScreen;

    @FXML
    private JFXButton pay;

    @FXML
    private JFXButton seance;

    @FXML
    private JFXButton glasses;

    @FXML
    private JFXButton control;

    @FXML
    void OnClicked_Control(MouseEvent event) throws IOException {
        initFXMLWindow(pay, "/coursework/controllers/controlScreen.fxml","Билет", 0,0);
    }

    @FXML
    void OnClicked_Pay(MouseEvent event) throws IOException {
        initFXMLWindow(pay, "/coursework/controllers/addScreen.fxml","Билет", 0,0);
    }

    @FXML
    void onClicked_Glasses(MouseEvent event) {
        if (glasses.getText().equals("Выдать очки")) {
            buttonScreen.setStyle("-fx-border-color:  #292929; -fx-border-radius: 5; -fx-text-fill: green");
        }
    }

    @FXML
    void onClicked_Screen(MouseEvent event) {
        if (buttonScreen.getText().equals("Экран выключен")) {
            buttonScreen.setText("Экран включен");
            buttonScreen.setStyle("-fx-border-color:  #292929; -fx-border-radius: 5; -fx-text-fill: green");
        }
        else {
            buttonScreen.setText("Экран выключен");
            buttonScreen.setStyle("-fx-border-color:  #292929; -fx-border-radius: 5; -fx-text-fill: red");
        }
    }

    @FXML
    void onClicked_Seance(MouseEvent event) {

    }

    @FXML
    void initialize() throws RemoteException {

        seatList = client.getSeatList(session.getHall().getHall().getId());
        hallList = client.getHallList(session.getHall().getHall().getId());

        setSeat();
    }

    private Label setText(Label text, int i) {
        text = new Label();
        text.setStyle("-fx-font-family: Cambria; -fx-font-size: 22;");
        text.setText("" + i);
        return text;
    }

    private int check = 0;
    private Button button = null;
    private void setSeat() throws RemoteException {
        this.seatList = seatList;
        this.hallList = hallList;

        HBox hBox = null;
        //Button button = null;
        Label text = null;

        for (int i = 1; i <= hallList.get(0).getRowTotal(); i++) {

            boxIdRight.getChildren().add(setText(text, i));
            boxIdLeft.getChildren().add(setText(text, i));

            hBox = new HBox();
            hBox.setSpacing(6);
            hBox.setPrefHeight(62);
            hBox.setAlignment(Pos.CENTER);


            int seat = client.getSeatByRow(i, session.getHall().getHall().getId());

            for (int j = 1; j <= seat; j++) {
                button = new Button("" + j);

                button.setPrefWidth(55);
                button.setPrefHeight(55);

                check++;
                if(seatList.get(i).getCategory().getCategoryName().equals("VIP")) {
                    button.setStyle("-fx-background-radius: 15; -fx-background-color:  green; -fx-text-fill: white;" +
                            "-fx-font-family: Cambria ; -fx-font-size: 20; -fx-font-weight: bold;");
                }
                else if (seatList.get(i).getCategory().getCategoryName().equals("Детский")) {
                    button.setStyle("-fx-background-radius: 15; -fx-background-color:  red; -fx-text-fill: white;" +
                            "-fx-font-family: Cambria ; -fx-font-size: 20; -fx-font-weight: bold;");
                }
                else {
                    button.setStyle("-fx-background-radius: 15; -fx-background-color:  #013f78; -fx-text-fill: white;" +
                            "-fx-font-family: Cambria ; -fx-font-size: 20; -fx-font-weight: bold;");
                }

                paint();

                Button finalButton = button;
                Label finalText = setText(text,i);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        pay.setDisable(false);
                        control.setDisable(false);
                        finalButton.setStyle("-fx-background-radius: 15; -fx-background-color:  gray; -fx-text-fill: white; " +
                                "-fx-font-family: Cambria ; -fx-font-size: 20; -fx-font-weight: bold;");
                        label.setText(label.getText() + "Ряд:" + finalText.getText() + " Место: " + finalButton.getText() + "\n");
                        //session.setSeat(Integer.parseInt(finalText.getText()), Integer.parseInt(finalButton.getText()));
                    }
                });
                hBox.getChildren().add(button);
            }
            boxSeat.getChildren().addAll(hBox);
        }
    }
    private void paint() {
        if (check == 4 || check == 5 || check == 6 || check == 11 || check == 12 || check == 24 || check == 25)
        {
            button.setStyle("-fx-background-radius: 15; -fx-background-color:  green; -fx-text-fill: white;" +
                    "-fx-font-family: Cambria ; -fx-font-size: 20; -fx-font-weight: bold;");
        }

        if (check == 28 || check == 29) {
            button.setStyle("-fx-background-radius: 15; -fx-background-color:  red; -fx-text-fill: white;" +
                    "-fx-font-family: Cambria ; -fx-font-size: 20; -fx-font-weight: bold;");
        }
    }
}
