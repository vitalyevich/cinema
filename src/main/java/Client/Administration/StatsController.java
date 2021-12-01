package Client.Administration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.Model.Open;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class StatsController extends Open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor;

    @FXML
    void initialize() throws IOException {
        initMenu(anchor);
    }
}
