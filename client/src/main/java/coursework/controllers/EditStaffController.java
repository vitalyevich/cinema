package coursework.controllers;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.validation.RequiredFieldValidator;
import coursework.interfaces.Verifiable;
import coursework.models.Access;
import coursework.models.AccessId;
import coursework.models.Department;
import coursework.operations.Notification;
import coursework.models.Staff;
import coursework.operations.OperationWithUserImpl;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class EditStaffController implements Verifiable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    private Notification notification = new Notification();

    private OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
    @FXML
    void onClicked_Save(MouseEvent event) {
        try {
            if (editLastName.getText().isEmpty() || editFirstName.getText().isEmpty() ||
                    editMiddleName.getText().isEmpty() ||
                    editPhone.getText().isEmpty()) {
                throw new NullPointerException();
            }

            String password = session.getAccess().getPassword();

            if (!editPass.getText().equals("")) {
                password = BCrypt.hashpw(editPass.getText().trim(), BCrypt.gensalt(12));
            }

            AccessId access = new AccessId();
            Department department = new Department();
            department.setId(findDepId(editComboDep.getValue()));
            access.setDepartmentId(department);
            Staff staff = new Staff();
            staff.setId(session.getAccess().getId().getStaffId().getId());
            access.setStaffId(staff);

            notification.alert = new JFXAlert((Stage) editSave.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_EDIT, notification.BODY_EDIT,
                    notification.yesButton);

            String finalPassword = password;
            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {

                    operationWithUser.EditStaff(new Staff(staff.getId(),
                            editLastName.getText().trim(), editFirstName.getText().trim(), editMiddleName.getText().trim(),
                            editPhone.getText().trim(), editComboGender.getValue()),new Access(access, finalPassword));

                    editSave.getScene().getWindow().hide();

                    notification.getSuccess(editSave,notification.HEAD_EDIT,
                            notification.SUCCESS_EDIT);

                } catch (RemoteException e) {
                    notification.getError(editSave, notification.HEAD_EDIT, notification.ERROR_CONNECT);
                } catch (SQLException e) {
                    notification.getError(editSave, notification.HEAD_EDIT, notification.ERROR);
                }
            });

        } catch (NullPointerException e) {
            notification.getError(editSave, notification.HEAD_EDIT, notification.ERROR);
        }
    }

    private int findDepId(String name) {

        for (int i = 0; i < departmentData.size(); i++) {
            if (name.equals(departmentData.get(i).getPosition()))
                return departmentData.get(i).getId();
        }
        return 0;
    }

    private BillingClient client = new BillingClient();
    private List<Department> departmentData = new ArrayList<>();

    private Session session = Session.getInstance();

    @FXML
    void initialize() throws RemoteException {
        validator();

        editComboGender.getItems().addAll('М','Ж');

        departmentData = client.getDepartmentList();
        for (int i = 1; i < departmentData.size(); i++) {
            editComboDep.getItems().add(departmentData.get(i).getPosition());
        }

        editFirstName.setText(session.getAccess().getFirstName());
        editLastName.setText(session.getAccess().getLastName());
        editMiddleName.setText(session.getAccess().getMiddleName());
        editPhone.setText(session.getAccess().getPhone());
        editComboDep.getSelectionModel().select(session.getAccess().getId().getDepartmentId().getId() - 2);
        if (session.getAccess().getGender().equals('М')) {
            editComboGender.getSelectionModel().select(0);
        }
        else {
            editComboGender.getSelectionModel().select(1);
        }
    }

    @Override
    public void validator() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Фамилия пустая!");
        editLastName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Имя пустое!");
        editFirstName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Отчество пустое!");
        editMiddleName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Номер пустой!");
        editPhone.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Пароль пустой!");
        editPass.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Пол пустой!");
        editComboGender.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Должность пустая!");
        editComboDep.getValidators().add(validator);

        editPhone.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                editPhone.validate();
            }
        });

        editLastName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                editLastName.validate();
            }
        });

        editFirstName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                editFirstName.validate();
            }
        });

        editMiddleName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                editMiddleName.validate();
            }
        });

        editPass.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                editPass.validate();
            }
        });

        editComboGender.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                editComboGender.validate();
            }
        });

        editComboDep.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                editComboDep.validate();
            }
        });
    }
}
