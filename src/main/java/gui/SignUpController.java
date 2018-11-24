package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userManagement.User;

import java.net.URL;

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
        if (!password.getText().equals(passwordRepetition.getText())) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.CANCEL).show();
        } else {
            System.out.println(new User(username.getText(), password.getText().toCharArray()));
            System.exit(0);
        }
    }

    @FXML
    void mainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:src/main/java/gui/application.fxml"));
            ((Stage) scene.getWindow()).setScene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
