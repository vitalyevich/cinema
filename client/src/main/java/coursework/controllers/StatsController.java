package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import coursework.models.OrderProduct;
import coursework.models.OrderTicket;
import coursework.operations.Open;
import coursework.rmi.BillingClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class StatsController extends Open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<String, Number> Chart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private ComboBox<String> choice;

    @FXML
    private AnchorPane anchor;

    private List<OrderProduct> orderProductList = new ArrayList<>();
    private BillingClient client = new BillingClient();
    @FXML
    void onAction_Choice(ActionEvent event) throws RemoteException {
        Chart.getData().removeAll(Chart.getData());
        XYChart.Series series = new XYChart.Series();
        series.setName("Оцениваемая продукция");
            orderProductList = client.getOrderList();
            for (int i = 0; i < orderProductList.size(); i++) {
                series.getData().add(new XYChart.Data<>("Заказ " + (1+i) +"", orderProductList.get(i).getMoney()));
            }

        Chart.getData().addAll(series);
    }

    @FXML
    void initialize() throws IOException {
        choice.getItems().addAll("Продажи буфета");
        initMenu(anchor);
    }
}
