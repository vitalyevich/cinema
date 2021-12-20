package coursework.controllers;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.validation.RequiredFieldValidator;
import coursework.interfaces.Verifiable;
import coursework.models.AccessId;
import coursework.models.Department;
import coursework.operations.Notification;
import coursework.models.Access;
import coursework.models.Staff;
import coursework.operations.OperationWithUserImpl;
import coursework.rmi.BillingClient;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.text.MaskFormatter;

public class AddStaffController implements Verifiable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    private Notification notification = new Notification();

    private OperationWithUserImpl operationWithUser = new OperationWithUserImpl();

    private List<Department> departmentData = new ArrayList<>();

    private int findDepId(String name) {

        for (int i = 0; i < departmentData.size(); i++) {
            if (name.equals(departmentData.get(i).getPosition()))
                return departmentData.get(i).getId();
        }
        return 0;
    }

    @FXML
    void onClicked_Add(MouseEvent event) {
        try {
            AccessId access = new AccessId();
            Department department = new Department();
            department.setId(findDepId(addComboDep.getValue()));
            access.setDepartmentId(department);

            if (addLastName.getText().isEmpty() || addFirstName.getText().isEmpty() || addMiddleName.getText().isEmpty()
                    || addPass.getText().isEmpty() || addPhone.getText().isEmpty()) {
                throw new NullPointerException();
            }

            notification.alert = new JFXAlert((Stage) addStaff.getScene().getWindow());
            notification.menu(notification.alert, notification.HEAD_ADD,notification.BODY_ADD, notification.yesButton);

            notification.yesButton.setOnAction(ev -> {
                notification.alert.hideWithAnimation();

                try {
                    operationWithUser.AddNewStaff(new Staff(addLastName.getText().trim(), addFirstName.getText().trim(),
                            addMiddleName.getText().trim(), addPhone.getText().trim(),
                            addComboGender.getValue()),new Access(access, BCrypt.hashpw(addPass.getText().trim(), BCrypt.gensalt(12))));

                    addStaff.getScene().getWindow().hide();
                    notification.getSuccess(addStaff,notification.HEAD_ADD, notification.SUCCESS_ADD);

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

    private BillingClient client = new BillingClient();
    @FXML
    void initialize() throws RemoteException {

        validator();

        addComboGender.getItems().addAll('М','Ж');

        departmentData = client.getDepartmentList();
        for (int i = 1; i < departmentData.size(); i++) {
            addComboDep.getItems().add(departmentData.get(i).getPosition());
        }
    }

    @Override
    public void validator() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Фамилия пустая!");
        addLastName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Имя пустое!");
        addFirstName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Отчество пустое!");
        addMiddleName.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Номер пустой!");
        addPhone.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Пароль пустой!");
        addPass.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Пол пустой!");
        addComboGender.getValidators().add(validator);

        validator = new RequiredFieldValidator();
        validator.setMessage("Должность пустая!");
        addComboDep.getValidators().add(validator);

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

        addPhone.textProperty().addListener((o, oldVal, newVal) -> {
            if (addPhone.getText().length() == 11 && oldVal.length() < newVal.length()) {
                String str = addPhone.getText();
                StringBuilder stringBuilder = new StringBuilder("x(xxx)-xxx-xx-xx");
                stringBuilder.setCharAt(0, str.charAt(0));
                stringBuilder.setCharAt(1, '(');
                stringBuilder.setCharAt(2, str.charAt(1));
                stringBuilder.setCharAt(3, str.charAt(2));
                stringBuilder.setCharAt(4, str.charAt(3));
                stringBuilder.setCharAt(5, ')');
                stringBuilder.setCharAt(6, '-');
                stringBuilder.setCharAt(7, str.charAt(4));
                stringBuilder.setCharAt(8, str.charAt(5));
                stringBuilder.setCharAt(9, str.charAt(6));
                stringBuilder.setCharAt(10, '-');
                stringBuilder.setCharAt(11, str.charAt(7));
                stringBuilder.setCharAt(12, str.charAt(8));
                stringBuilder.setCharAt(13, '-');
                stringBuilder.setCharAt(14, str.charAt(9));
                stringBuilder.setCharAt(15, str.charAt(10));
                addPhone.setText(stringBuilder.toString());
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
    }

}
