package coursework.interfaces;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;

public interface Notifiable {

    public void getError(JFXButton button, String head, String body);
    public void getSuccess(JFXButton button, String head, String body);
    public void menu(JFXAlert alert, String head, String body, JFXButton yesButton);
}
