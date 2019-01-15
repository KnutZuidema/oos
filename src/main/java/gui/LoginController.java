package gui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userManagement.User;

import java.io.IOException;

public class LoginController extends WarningController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    CheckBox newLogin;
    private boolean isNewLogin;

    @FXML
    void initialize() {
        super.initialize();
        newLogin.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            isNewLogin = newValue;
        }));
    }

    @FXML
    void login() {
        if (isNewLogin) {
            loadTemplate("file:src/main/resources/signup.fxml");
        } else {
            User user = new User(username.getText(), password.getText().toCharArray());
            System.out.println(user);
            try {
                if (mainApplication.getUserAdmin().verifyUser(user)) {
                    displaySuccess("Logged in successfully");
                } else {
                    shakeError("Invalid credentials");
                }
            } catch (IOException | ClassNotFoundException e) {
                shakeError("Could not access database");
            }
        }
    }

    @FXML
    void mainMenu() {
        loadTemplate("file:src/main/resources/application.fxml");
    }
}

