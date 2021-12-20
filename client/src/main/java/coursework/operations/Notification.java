package coursework.operations;

import coursework.interfaces.Notifiable;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Notification implements Notifiable {

    public JFXButton yesButton = new JFXButton("ДА");
    public JFXAlert alert;

    public final String BODY_ADD = "Вы подтверждаете добавление данных?";
    public final String BODY_EDIT = "Вы подтверждаете редактирование данных?";
    public final String BODY_DEL = "Вы подтверждаете удаление данных?";

    public final String HEAD_ADD = "Добавление данных в БД";
    public final String HEAD_EDIT = "Редактирование данных в БД";
    public final String HEAD_DEL = "Удаление данных в БД";

    public final String ERROR_CONNECT = "Соединение с сервером потеряно. Пожалуйста, повторите запрос позже!";

    public final String SUCCESS_ADD = "Данные успешно добавлены!";
    public final String SUCCESS_EDIT = "Данные успешно отредактированы!";
    public final String SUCCESS_DEL = "Данные успешно удалены!";

    public final String ERROR = "Произошла ошибка. Пожалуйста, проверьте ваши данные";

    @Override
    public void getError(JFXButton button, String head, String body) {
        new Thread( () -> {
            Platform.runLater( () -> {
                JFXAlert alert = new JFXAlert((Stage) button.getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setOverlayClose(false);
                JFXDialogLayout layout = new JFXDialogLayout();
                layout.setHeading(new Label(head));
                layout.setBody(new Label(body));
                JFXButton cancelButton = new JFXButton("ОК");
                cancelButton.getStyleClass().add("dialog-accept");

                cancelButton.setOnAction(ev -> {
                    alert.hideWithAnimation();
                });
                layout.setActions(cancelButton);
                alert.setContent(layout);
                alert.show();
            });
        }).start();
    }

    @Override
    public void getSuccess(JFXButton button, String head, String body) {
        new Thread( () -> {
            Platform.runLater( () -> {
                JFXAlert alert = new JFXAlert((Stage) button.getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setOverlayClose(false);
                JFXDialogLayout layout = new JFXDialogLayout();
                layout.setHeading(new Label(head));
                layout.setBody(new Label(body));
                JFXButton cancelButton = new JFXButton("ОК");
                cancelButton.getStyleClass().add("dialog-accept");

                cancelButton.setOnAction(ev -> {
                    alert.hideWithAnimation();
                });
                layout.setActions(cancelButton);
                alert.setContent(layout);
                alert.show();
            });
        }).start();
    }

    @Override
    public void menu(JFXAlert alert, String head, String body, JFXButton yesButton) {
        new Thread( () -> {
            Platform.runLater( () -> {
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setOverlayClose(false);
                JFXDialogLayout layout = new JFXDialogLayout();
                layout.setHeading(new Label(head));
                layout.setBody(new Label(body));
                JFXButton notButton = new JFXButton("НЕТ");
                yesButton.getStyleClass().add("dialog-yes");
                notButton.getStyleClass().add("dialog-not");

                notButton.setOnAction(event -> {
                    alert.hideWithAnimation();
                });
                layout.setActions(yesButton, notButton);
                alert.setContent(layout);
                alert.show();
            });
        }).start();
    }
}
