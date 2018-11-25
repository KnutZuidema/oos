package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    void signUp() {
        if (!password.getText().equals(passwordRepetition.getText())) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.CLOSE).show();
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
        passwordRepetition.textProperty().addListener(((observable, oldValue, newValue) -> {
            passwordWarning.setVisible(!newValue.equals(password.getText()));
        }));
    }
}
