package coursework.interfaces;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public interface Openable {

    public void initFXMLWindow(JFXButton buttonClick, String path, String name, int clOp, int size) throws IOException;
    public void initFXML(JFXButton buttonClick, String path, int clOp) throws IOException;
    public void initMenu(AnchorPane anchorPane) throws IOException;

}
