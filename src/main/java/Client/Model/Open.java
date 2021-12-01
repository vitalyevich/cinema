package Client.Model;

import Client.Administration.CoverController;
import Client.Interfaces.Openable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class Open implements Openable {

    private Parent root;
    private double xOffset, yOffset;

    @Override
    public void initFXMLWindow(JFXButton buttonClick, String path, String name, int clOp, int size) throws IOException {
        if(clOp == 1) {
            buttonClick.getScene().getWindow().hide();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
       // stage.setResizable(false);
        stage.setTitle(name);

        if (size == 2) { // 1
            stage.setFullScreen(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setFullScreenExitHint("");
        }
        stage.show();

    }

    public void initFXML(JFXButton buttonClick, String path, int clOp) throws IOException {

        if(clOp == 1) {
            buttonClick.getScene().getWindow().hide();
        }

        try {
        root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
        });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AnchorPane anchor;
    @Override
    public void initMenu(AnchorPane anchorPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("cover.fxml"));
        anchor = fxmlLoader.load();
        anchorPane.getChildren().setAll(anchor);

    }
}
