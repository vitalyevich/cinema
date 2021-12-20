package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import coursework.models.Ticket;
import coursework.models.TicketsPrice;
import coursework.operations.Open;
import coursework.rmi.BillingClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TicketController extends Open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Ticket> tableTicket;

    @FXML
    private TableColumn<Ticket, Integer> numColumn;

    @FXML
    private TableColumn<Ticket, Integer> seanceColumn;

    @FXML
    private TableColumn<Ticket, String> hallColumn;

    @FXML
    private TableColumn<Ticket, Integer> rowColumn;

    @FXML
    private TableColumn<Ticket, Integer> seatColumn;

    @FXML
    private TableColumn<Ticket, String> statusColumn;

    @FXML
    private TableView<TicketsPrice> tablePrice;

    @FXML
    private TableColumn<TicketsPrice, Integer> numberColumn;

    @FXML
    private TableColumn<TicketsPrice, String> categoryColumn;

    @FXML
    private TableColumn<TicketsPrice, Long> priceColumn;

    @FXML
    private AnchorPane anchor;

    @FXML
    void initialize() throws IOException {
        initMenu(anchor);
        fillingPriceTable();
        fillingTicketTable();
    }

    private BillingClient client = new BillingClient();
    private ObservableList<TicketsPrice> ticketsPrices = FXCollections.observableArrayList();
    private ObservableList<Ticket> tickets = FXCollections.observableArrayList();

    private void fillingPriceTable() throws RemoteException {
        ticketsPrices = FXCollections.observableArrayList(client.getTicketPriceList());
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("seanceId"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tablePrice.setItems(ticketsPrices);
    }

    private void fillingTicketTable() throws RemoteException {
        tickets = FXCollections.observableArrayList(client.getTicketList());
        numColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        seanceColumn.setCellValueFactory(new PropertyValueFactory<>("seanceId"));
        hallColumn.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        rowColumn.setCellValueFactory(new PropertyValueFactory<>("rowId"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seatId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("ticketStatus"));
        tableTicket.setItems(tickets);
    }
}
