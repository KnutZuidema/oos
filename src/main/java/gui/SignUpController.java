package gui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import userManagement.User;

public class SignUpController extends Controller {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField passwordRepetition;
    @FXML
    Label passwordWarning;
    TranslateTransition shake;

    @FXML
    void signUp() {
        if (!password.getText().equals(passwordRepetition.getText())) {
            passwordWarning.setVisible(true);
            shake.playFromStart();
            passwordRepetition.setText("");
        } else {
            System.out.println(new User(username.getText(), password.getText().toCharArray()));
            System.exit(0);
        }
    }

    @FXML
    void mainMenu() {
        loadTemplate("file:src/main/java/gui/application.fxml");
    }

    @FXML
    void initialize() {
        shake = new TranslateTransition(new Duration(50), passwordWarning);
        shake.setFromX(0);
        shake.setByX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        passwordRepetition.textProperty().addListener(((observable, oldValue, newValue) -> {
            passwordWarning.setVisible(!newValue.equals(password.getText()));
        }));
    }
}
