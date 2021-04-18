package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    TextArea chatTextArea;
    @FXML
    TextField messageField;

    public void clickSendButton(ActionEvent actionEvent) {
        if (!messageField.getText().equals("")) {
            chatTextArea.appendText(messageField.getText() + "\n");
            messageField.clear();
        }
    }

    public void enterReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!messageField.getText().equals("")) {
                chatTextArea.appendText(messageField.getText() + "\n");
                messageField.clear();
            }
        }
    }
}
