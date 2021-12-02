package Client.Administration;

import Client.Interfaces.Verifiable;
import Client.Model.Notification;
import Client.Model.Open;
import Client.RMI.BillingClient;
import Server.Model.Access;
import Server.Model.AccessId;
import Server.Model.Department;

import Server.Model.Staff;
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
import org.mindrot.jbcrypt.BCrypt;

public class StaffController extends Open implements Verifiable {

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
    private JFXTextField addLastName;

    @FXML
    private JFXTextField addFirstName;

    @FXML
    private JFXTextField addMiddleName;

    @FXML
    private JFXTextField addPass;

    @FXML
    private JFXComboBox<Character> addComboGender;

    @FXML
    private JFXTextField addPhone;

    @FXML
    private JFXComboBox<String> addComboDep;

    @FXML
    private JFXButton addStaff;

    @FXML
    private JFXComboBox<Integer> editComboNum;

    @FXML
    private JFXTextField editLastName;

    @FXML
    private JFXTextField editFirstName;

    @FXML
    private JFXTextField editMiddleName;

    @FXML
    private JFXTextField editPass;

    @FXML
    private JFXComboBox<Character> editComboGender;

    @FXML
    private JFXTextField editPhone;

    @FXML
    private JFXComboBox<String> editComboDep;

    @FXML
    private JFXButton editSave;

    @FXML
    private JFXButton buttonDel;

    @FXML
    private JFXButton buttonDelId;

    @FXML
    private JFXComboBox<Integer> delComboNum;

    @FXML
    private JFXTextField delLastName;

    @FXML
    private JFXTextField delFirstName;

    @FXML
    private JFXTextField delMiddleName;

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

    private int depId;

    private int findDepId(String name) {

        for (int i = 0; i < departmentData.size(); i++) {
            if (name.equals(departmentData.get(i).getPosition())) depId = departmentData.get(i).getId();
        }
        return depId;
    }

    private Notification notification = new Notification();

    private AccessId access = new AccessId();

    @FXML
    void onClicked_Add(MouseEvent event) {

        try {
            department.setId(findDepId(addComboDep.getValue()));
            access.setDepartmentId(department);
            staff.setId(accessData.get(accessData.size() - 1).getId().getStaffId().getId() + 1); //?
            access.setStaffId(staff);

            if (addLastName.getText().isEmpty() || addFirstName.getText().isEmpty() || addMiddleName.getText().isEmpty()
                    || addPass.getText().isEmpty() || addPhone.getText().isEmpty()) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) addStaff.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD,notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.AddNewStaff(new Staff(addLastName.getText().trim(), addFirstName.getText().trim(),
                            addMiddleName.getText().trim(), addPhone.getText().trim(),
                            addComboGender.getValue()));

                    client.AddNewAccess(new Access(access,BCrypt.hashpw(addPass.getText().trim(), BCrypt.gensalt(12))));

                    notification.getSuccess(addStaff,notification.HEAD_ADD, notification.SUCCESS_ADD);

                    clearText();
                    fillingTableStaff();

                } catch (RemoteException e) {
                    notification.getError(addStaff,  notification.HEAD_ADD, notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(addStaff,  notification.HEAD_ADD, notification.ERROR);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(addStaff,  notification.HEAD_ADD, notification.ERROR);
        }
    }

    void clearText() {

        addLastName.setText("");
        addFirstName.setText("");
        addMiddleName.setText("");
        addPhone.setText("");
        addPass.setText("");

        editLastName.setText("");
        editFirstName.setText("");
        editPhone.setText("");
        editPass.setText("");

        delLastName.setText("");
        delFirstName.setText("");
        delMiddleName.setText("");

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

                    fillingTableStaff();
                    clearText();

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

    @FXML
    void onClicked_Del(MouseEvent event) {

        if(!delLastName.getText().isEmpty() && !delFirstName.getText().isEmpty() && !delMiddleName.getText().isEmpty()) {

            notification.alert = new JFXAlert((Stage) buttonDel.getScene().getWindow());

            notification.menu(notification.alert, notification.HEAD_DEL,
                    notification.BODY_DEL, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    client.DeleteStaff(delLastName.getText(), delFirstName.getText(), delMiddleName.getText());

                    notification.getSuccess(addStaff,notification.HEAD_DEL, notification.SUCCESS_DEL);

                    clearText();
                    fillingTableStaff();

                } catch (SQLException e) {
                    notification.getError(buttonDel, notification.HEAD_DEL, notification.ERROR);
                } catch (RemoteException e) {
                    notification.getError(buttonDel, notification.HEAD_DEL, notification.ERROR_CONNECT);
                }

            });

        }
        else {
            notification.getError(buttonDel, notification.HEAD_DEL, notification.ERROR);
        }
    }

    @FXML
    void onClicked_DelId(MouseEvent event) {

        try {
            if (delComboNum.getValue().equals("")) { }
                notification.alert = new JFXAlert((Stage) buttonDel.getScene().getWindow());
                notification.menu(notification.alert, notification.HEAD_DEL,
                        notification.BODY_DEL, notification.yesButton);

                notification.yesButton.setOnAction(ev -> {
                    notification.alert.hideWithAnimation();
                    try {
                        client.DeleteStaffById(Integer.parseInt(String.valueOf(delComboNum.getValue())));
                        notification.getSuccess(buttonDelId, notification.HEAD_DEL, notification.SUCCESS_DEL);

                        fillingTableStaff();
                        clearText();

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

    @FXML
    void onAction_ComboNum(ActionEvent event) throws RemoteException {

        List<Access> accessList = client.getStaff(Integer.parseInt(String.valueOf(editComboNum.getValue())));
        editFirstName.setText(accessList.get(0).getFirstName());
        editLastName.setText(accessList.get(0).getLastName());
        editMiddleName.setText(accessList.get(0).getMiddleName());
        editPhone.setText(accessList.get(0).getPhone());

        editComboDep.getSelectionModel().select(accessList.get(0).getId().getDepartmentId().getId() - 2);

        if (accessList.get(0).getId().getStaffId().getGender().equals('М')) {
            editComboGender.getSelectionModel().select(0);
        } else {
            editComboGender.getSelectionModel().select(1);
        }
    }

    @FXML
    void onClicked_Save(MouseEvent event) {

        try {

            if (editLastName.getText().isEmpty() || editFirstName.getText().isEmpty() ||
                    editMiddleName.getText().isEmpty() || editPass.getText().isEmpty() ||
                    editPhone.getText().isEmpty()) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) addStaff.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT,
                    notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    //department.setId(findDepId(editComboDep.getValue()));
                    // BCrypt.hashpw(editPass.getText().trim(), BCrypt.gensalt(12))

                    client.EditStaff(new Staff(Integer.parseInt(String.valueOf(editComboNum.getValue())),
                            editLastName.getText().trim(), editFirstName.getText().trim(), editMiddleName.getText().trim(),
                            editPhone.getText().trim(), editComboGender.getValue()));

                    notification.getSuccess(editSave,notification.HEAD_EDIT,
                            notification.SUCCESS_EDIT);

                    clearText();
                    fillingTableDepartment();

                } catch (RemoteException e) {
                    notification.getError(editSave, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(editSave, notification.HEAD_EDIT, notification.ERROR);
        }
    }

    @FXML
    void onClicked_Update(MouseEvent event) {
        fillingTableStaff();
        fillingTableDepartment();
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

    @Override
    public void validator() {

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Фамилия пустая!");
        addLastName.getValidators().add(validator);
        editLastName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Имя пустое!");
        addFirstName.getValidators().add(validator);
        editFirstName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Отчество пустое!");
        addMiddleName.getValidators().add(validator);
        editMiddleName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Номер пустой!");
        addPhone.getValidators().add(validator);
        editComboNum.getValidators().add(validator);
        editPhone.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Пароль пустой!");
        addPass.getValidators().add(validator);
        editPass.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Пол пустой!");
        addComboGender.getValidators().add(validator);
        editComboGender.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Должность пустая!");
        addComboDep.getValidators().add(validator);
        editComboDep.getValidators().add(validator);

        addLastName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addLastName.validate();
            }
        });
        addFirstName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addFirstName.validate();
            }
        });
        addMiddleName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addMiddleName.validate();
            }
        });
        addPass.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addPass.validate();
            }
        });
        addPhone.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addPhone.validate();
            }
        });

        addComboGender.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboGender.validate();
            }
        });
        addComboDep.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editComboNum.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editPhone.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editLastName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editFirstName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editMiddleName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editPass.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editComboGender.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

        editComboDep.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addComboDep.validate();
            }
        });

    }

    @FXML
    void initialize() throws IOException {

        initMenu(anchor);

        validator();

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

    //private ObservableList<Staff> staffData = FXCollections.observableArrayList();
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

        delComboNum.getItems().clear();
        editComboNum.getItems().clear();

        for (int i = 1; i < accessData.size(); i++) {

            delComboNum.getItems().add(accessData.get(i).getId().getStaffId().getId());
            editComboNum.getItems().add(accessData.get(i).getId().getStaffId().getId());
        }

        editComboGender.getItems().clear();
        addComboGender.getItems().clear();

        editComboGender.getItems().add('М');
        editComboGender.getItems().add('Ж');
        addComboGender.getItems().add('М');
        addComboGender.getItems().add('Ж');
    }

    private void fillingTableDepartment() {

        try {
            departmentData = FXCollections.observableArrayList(client.getDepartmentList(department));
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
        editComboDep.getItems().clear();
        addComboDep.getItems().clear();

        for (int i = 1; i < departmentData.size(); i++) {

            comboDep.getItems().add(departmentData.get(i).getPosition());
            comboDepartment.getItems().add(departmentData.get(i).getPosition());
            delComboDep.getItems().add(departmentData.get(i).getPosition());
            editComboDep.getItems().add(departmentData.get(i).getPosition());
            addComboDep.getItems().add(departmentData.get(i).getPosition());
        }
    }
}

