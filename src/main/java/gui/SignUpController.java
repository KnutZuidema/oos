package gui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import userManagement.User;
import userManagement.UserAlreadyExistsException;
import userManagement.UserManagerAdministrator;

import java.io.IOException;

public class SignUpController extends WarningController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField passwordRepetition;
    @FXML
    Label warning;
    TranslateTransition shake;
    UserManagerAdministrator userAdmin;

    @FXML
    void signUp() {
        if (!password.getText().equals(passwordRepetition.getText())) {
            warning.setVisible(true);
            shake.playFromStart();
            passwordRepetition.setText("");
        } else {
            User user = new User(username.getText(), password.getText().toCharArray());
            try {
                userAdmin.addUser(user);
                System.out.println("Added " + user);
                mainMenu();
            } catch (UserAlreadyExistsException e) {
                warning.setText("User already exists");
                warning.setVisible(true);
                shake.playFromStart();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void mainMenu() {
        loadTemplate("file:src/main/resources/application.fxml");
    }
}
