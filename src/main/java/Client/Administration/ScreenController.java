package Client.Administration;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import Client.RMI.BillingClient;
import Server.Model.Hall;
import Server.Model.Seat;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScreenController extends FilmController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox boxId;

    @FXML
    private VBox boxSeat;

    @FXML
    private Label label;

    private List<Seat> seatList;
    private List<Hall> hallList;

    @FXML
    void initialize() throws RemoteException {

        seatList = client.getSeatList(); // test
        hallList = client.getHallList(); // test
        setSeat();
    }

    private void setSeat() throws RemoteException { //List<Seat> seatList, List<Hall> hallList
        this.seatList = seatList;
        this.hallList = hallList;

        HBox hBox = null;
        Button button = null;
        Text text = null;

        for (int i = 1; i <= hallList.get(0).getRowTotal(); i++) {

            text = new Text();
            text.setText("" + i);
            boxId.getChildren().addAll(text);

            hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setPrefHeight(67);
            hBox.setAlignment(Pos.CENTER);


            int seat = client.getSeatByRow(i);

            for (int j = 1; j <= seat; j++) {
                button = new Button("" + j);

                button.setPrefWidth(60);
                button.setPrefHeight(60);
                button.setStyle("-fx-background-radius: 17; -fx-background-color:  #013f78; -fx-text-fill: white");

                Button finalButton = button;
                Text finalText = text;

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        finalButton.setDisable(true);
                        label.setText(label.getText() + "Ряд:" + finalText.getText() + " Место: " + finalButton.getText() + "\n");
                    }
                });
                button.setOnMouseMoved(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        finalButton.setStyle("-fx-background-color: green; -fx-background-radius: 17; -fx-text-fill: white");
                    }
                });
                button.setOnMouseExited(new EventHandler() {

                    @Override
                    public void handle(Event event) {
                        finalButton.setStyle("-fx-background-color: #013f78; -fx-background-radius: 17; -fx-text-fill: white");
                    }
                });
                hBox.getChildren().add(button);
            }
            boxSeat.getChildren().addAll(hBox);
        }
    }
}
