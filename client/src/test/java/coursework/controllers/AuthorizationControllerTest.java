package coursework.controllers;

import com.jfoenix.controls.JFXButton;
import coursework.operations.Open;
import de.saxsys.javafx.test.JfxRunner;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JfxRunner.class)
class AuthorizationControllerTest {

    private Parent root;
    private double xOffset, yOffset;

    public void setFXML() {
        Platform.runLater(() -> {
        try {
                try {
                    root = FXMLLoader.load(getClass().getResource("/coursework/authorization.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        });
    }

    @Test
    void connectivity() {
       AuthorizationController authorizationController = new AuthorizationController();
       authorizationController.connectivity();
    }

    private int counter = 5;

    @Test
    void setTimer() {
        new JFXPanel();
        setFXML();
        AuthorizationController authorizationController = new AuthorizationController();
        authorizationController.setTimer(counter);

        int expected = 5;
        Assert.assertEquals(expected, counter);
    }
}