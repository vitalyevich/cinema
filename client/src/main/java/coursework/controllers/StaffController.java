package coursework.controllers;

import coursework.interfaces.Verifiable;
import coursework.operations.Notification;
import coursework.operations.Open;
import coursework.operations.OperationWithUserImpl;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import coursework.models.Access;
import coursework.models.AccessId;
import coursework.models.Department;

import coursework.models.Staff;
import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StaffController extends Open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField textFind;

    @FXML
    private JFXButton buttonUpdate;

    @FXML
    private TableView<Access> tableStaff;

    @FXML
    private TableColumn<Access, Integer> numColumn;

    @FXML
    private TableColumn<Access, String> lastNameColumn;

    @FXML
    private TableColumn<Access, String> firstNameColumn;

    @FXML
    private TableColumn<Access, String> middleNameColumn;

    @FXML
    private TableColumn<Access, String> phoneColumn;

    @FXML
    private TableColumn<Access, String> depColumn;

    @FXML
    private TableColumn<Access, Long> salColumn;

    @FXML
    private JFXButton addStaff;

    @FXML
    private JFXButton editSave;

    @FXML
    private JFXButton buttonDelId;

    @FXML
    private JFXComboBox<String> delComboDep;

    @FXML
    private JFXButton buttonAllDel;

    @FXML
    private JFXButton buttonClear;

    @FXML
    private TableView<Department> tableDepartment;

    @FXML
    private TableColumn<Department, Integer> numberColumn;

    @FXML
    private TableColumn<Department, String> departmentColumn;

    @FXML
    private TableColumn<Department, Long> salaryColumn;

    @FXML
    private JFXComboBox<String> comboDep;

    @FXML
    private JFXTextField textSalary;

    @FXML
    private JFXButton editSalary;

    @FXML
    private JFXButton editDepartment;

    @FXML
    private JFXTextField textDep;

    @FXML
    private JFXComboBox<String> comboDepartment;

    @FXML
    private JFXButton addDep;

    @FXML
    private JFXButton delDep;

    @FXML
    private JFXButton clearDep;

    @FXML
    private JFXTextField textDepart;

    @FXML
    private JFXTextField textSal;

    @FXML
    private AnchorPane anchor;

    @FXML
    void onClicked_addDep(MouseEvent event) {

        try {

            if (textSal.getText().equals("") || textDepart.getText().equals("")) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) addDep.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD, notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    client.AddNewDepartment(new Department(textDepart.getText(), Long.parseLong(textSal.getText())));

                    notification.getSuccess(addDep, notification.HEAD_ADD, notification.SUCCESS_ADD);

                    clearText();
                    fillingTableDepartment();

                } catch (RemoteException e) {
                    notification.getError(addDep, notification.HEAD_ADD, notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(addDep, notification.HEAD_ADD, notification.ERROR);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(addDep, notification.HEAD_ADD, notification.ERROR);
        }
    }

    @FXML
    void onClicked_delDep(MouseEvent event) {

        try {

            if (comboDepartment.getValue().equals("")) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) delDep.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    client.DeleteDepartment(findDepId(comboDepartment.getValue()));

                    notification.getSuccess(delDep, notification.HEAD_DEL, notification.SUCCESS_DEL);

                    clearText();
                    fillingTableDepartment();

                } catch (RemoteException e) {
                    notification.getError(delDep, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(delDep, notification.HEAD_DEL, notification.ERROR);
        }

    }

    @FXML
    void onClicked_clearDep(MouseEvent event) {

        notification.alert = new JFXAlert((Stage) clearDep.getScene().getWindow());
        notification.menu(notification.alert,
                notification.HEAD_DEL,
                notification.BODY_DEL,
                notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableDepartment();
                notification.getSuccess(clearDep,
                        notification.HEAD_DEL,
                        notification.SUCCESS_DEL);

                clearText();
                fillingTableDepartment();
                fillingTableStaff();

            } catch (RemoteException e) {
                notification.getError(clearDep,
                        notification.HEAD_DEL,
                        notification.ERROR_CONNECT);
            }
        });
    }


    private int findDepId(String name) {

        for (int i = 0; i < departmentData.size(); i++) {
            if (name.equals(departmentData.get(i).getPosition()))
               return departmentData.get(i).getId();
        }
        return 0;
    }

    private Notification notification = new Notification();

    private AccessId access = new AccessId();

    @FXML
    void onClicked_Add(MouseEvent event) throws IOException {
        open.initFXMLWindow(addStaff, "/coursework/controllers/addStaff.fxml","Добавление сотрудника", 0, 0);
        editSave.setDisable(true);
        buttonDelId.setDisable(true);
    }

    void clearText() {
        textSalary.setText("");
        textDep.setText("");
        textSal.setText("");
    }

    @FXML
    void onClicked_AllDel(MouseEvent event) {

        try {

            if (delComboDep.getValue().equals("")) { }

            notification.alert = new JFXAlert((Stage) buttonAllDel.getScene().getWindow());

            notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                department.setId(findDepId(delComboDep.getValue()));

                try {
                    client.DeleteStaffByPosition(department);

                    notification.getSuccess(buttonAllDel, notification.HEAD_DEL, notification.SUCCESS_DEL);

                } catch (RemoteException e) {
                    notification.getError(buttonAllDel, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(buttonAllDel, notification.HEAD_DEL, notification.ERROR);
        }
    }

    @FXML
    void onClicked_Clear(MouseEvent event) {

        notification.alert = new JFXAlert((Stage) buttonClear.getScene().getWindow());
        notification.menu(notification.alert, notification.HEAD_DEL, notification.BODY_DEL, notification.yesButton);

        notification.yesButton.setOnAction(ev -> {
            notification.alert.hideWithAnimation();

            try {
                client.TruncateTableStaff();
                notification.getSuccess(buttonClear,notification.HEAD_DEL, notification.SUCCESS_DEL);

                clearText();
                fillingTableStaff();

            } catch (RemoteException e) {
                notification.getError(buttonClear, notification.HEAD_DEL, notification.ERROR_CONNECT);
            }
        });
    }

    private OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
    @FXML
    void onClicked_DelId(MouseEvent event) {

        try {
                notification.alert = new JFXAlert((Stage) buttonDelId.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_DEL,
                        notification.BODY_DEL, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();
                    try {
                        operationWithUser.DeleteStaff(tableStaff.getSelectionModel().getSelectedItem().getId().getStaffId().getId());
                        tableStaff.getItems().remove(tableStaff.getSelectionModel().getSelectedItem());
                        notification.getSuccess(buttonDelId, notification.HEAD_DEL, notification.SUCCESS_DEL);
                        editSave.setDisable(true);
                        buttonDelId.setDisable(true);

                    } catch (RemoteException e) {
                        notification.getError(buttonDelId, notification.HEAD_DEL, notification.ERROR_CONNECT);
                    }
                });
            } catch (NullPointerException e) {
            notification.getError(buttonDelId, notification.HEAD_DEL, notification.ERROR);
        }

    }

    @FXML
    void onClicked_EditSal(MouseEvent event) {

        try {

            if(textSalary.getText().equals("") || comboDep.getValue().equals("")) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) editSalary.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                department.setId(findDepId(comboDep.getValue()));
                department.setSalary(Long.parseLong(textSalary.getText().trim()));
                try {

                    client.EditDepSalary(department);
                    notification.getSuccess(editSalary, notification.HEAD_EDIT, notification.SUCCESS_EDIT);

                    clearText();
                    fillingTableDepartment();

                } catch (RemoteException e) {
                    notification.getError(editSalary, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(editSalary, notification.HEAD_EDIT, notification.ERROR);
        }
    }

    private Open open = new Open();
    private Session session = Session.getInstance();
    @FXML
    void onClicked_Save(MouseEvent event) throws IOException {
        session.setAccess(tableStaff.getSelectionModel().getSelectedItem());
        open.initFXMLWindow(editSave, "/coursework/controllers/editStaff.fxml","Редактирование сотрудника", 0, 0);
        editSave.setDisable(true);
        buttonDelId.setDisable(true);
    }

    @FXML
    void onClicked_Update(MouseEvent event) {
        fillingTableStaff();
        fillingTableDepartment();
    }

    @FXML
    void onClicked_TableStaff(MouseEvent event) {
        if (tableStaff.getSelectionModel().getSelectedItem() != null && tableStaff.getSelectionModel().getSelectedItem().getId().getStaffId().getId() != 1) {
            buttonDelId.setDisable(false);
            editSave.setDisable(false);
        } else {
            editSave.setDisable(true);
            buttonDelId.setDisable(true);
        }
    }

    @FXML
    void onClicked_editDep(MouseEvent event) {

        try {
            if (textDep.getText().equals("") || comboDepartment.getValue().equals("")) {
                throw new NullPointerException();
            }
            notification.alert = new JFXAlert((Stage) editDepartment.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    department.setId(findDepId(comboDepartment.getValue()));
                    department.setPosition(textDep.getText().trim());
                    client.EditDepartment(department);

                    notification.getSuccess(editDepartment, notification.HEAD_EDIT, notification.SUCCESS_EDIT);

                    clearText();
                    fillingTableDepartment();

                } catch (RemoteException e) {
                    notification.getError(editDepartment, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                }
            });
        } catch (NullPointerException e) {
            notification.getError(editDepartment, notification.HEAD_EDIT, notification.ERROR);
        }
    }

    @FXML
    void initialize() throws IOException {

        initMenu(anchor);

        new Thread(() -> {
            fillingTableStaff();
            fillingTableDepartment();
        }).start();

        textFind.textProperty().addListener((obs, oldText, newText) -> {
            if (!textFind.getText().isEmpty()) {
                tempData = FXCollections.observableArrayList();

                for (int i = 0; i < accessData.size(); i++) {
                    if (accessData.get(i).getLastName().toLowerCase().contains(textFind.getText().toLowerCase().trim())) {
                        tempData.add(accessData.get(i));
                    }
                }
                fillingColumnTable(tempData);
            } else {
                fillingColumnTable(accessData);
            }
        });
    }

    private ObservableList<Access> accessData = FXCollections.observableArrayList();
    private ObservableList<Access> tempData = FXCollections.observableArrayList();
    private BillingClient client = new BillingClient();
    private Staff staff = new Staff();

    private ObservableList<Department> departmentData = FXCollections.observableArrayList();
    private Department department = new Department();

    private void fillingColumnTable(ObservableList<Access> staffObservableList) {
        numColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        depColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        tableStaff.setItems(staffObservableList);
    }

    private void fillingTableStaff() {

        try {
            accessData = FXCollections.observableArrayList(client.getStaffList());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        fillingColumnTable(accessData);
    }

    private void fillingTableDepartment() {

        try {
            departmentData = FXCollections.observableArrayList(client.getDepartmentList());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableDepartment.setItems(departmentData);


        comboDep.getItems().clear();
        comboDepartment.getItems().clear();
        delComboDep.getItems().clear();

        for (int i = 1; i < departmentData.size(); i++) {

            comboDep.getItems().add(departmentData.get(i).getPosition());
            comboDepartment.getItems().add(departmentData.get(i).getPosition());
            delComboDep.getItems().add(departmentData.get(i).getPosition());
        }
    }
}

