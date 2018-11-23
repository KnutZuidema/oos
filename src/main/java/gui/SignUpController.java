package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userManagement.User;

public class SignUpController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField passwordRepetition;
    @FXML
    Scene scene;

    @FXML
    void signUp() {
        if(!password.getText().equals(passwordRepetition.getText())){
            new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.CANCEL).show();
        }else {
            System.out.println(new User(username.getText(), password.getText().toCharArray()));
            System.exit(0);
        }
    }
}
