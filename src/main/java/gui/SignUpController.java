package gui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userManagement.User;
import userManagement.UserAlreadyExistsException;

import java.io.IOException;

public class SignUpController extends WarningController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField passwordRepetition;

    @FXML
    void initialize() {
        super.initialize();
        passwordRepetition.textProperty().addListener(((observable, oldValue, newValue) -> {
            warning.setVisible(!newValue.equals(password.getText()));
        }));
    }

    @FXML
    void signUp() {
        if (!password.getText().equals(passwordRepetition.getText())) {
            warning.setVisible(true);
            shake.playFromStart();
            passwordRepetition.setText("");
        } else {
            User user = new User(username.getText(), password.getText().toCharArray());
            try {
                mainApplication.getUserAdmin().addUser(user);
                displaySuccess("User has been added to database");
            } catch (UserAlreadyExistsException e) {
                shakeError("User already exists");
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
