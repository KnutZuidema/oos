package gui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userManagement.User;

public class LoginController extends Controller {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    CheckBox newLogin;
    private boolean isNewLogin;

    @FXML
    void newLoginChanged() {
        this.isNewLogin = newLogin.isSelected();
        System.out.println(isNewLogin);
    }

    @FXML
    void login() {
        if (isNewLogin) {
            loadTemplate("file:src/main/java/gui/signup.fxml");
        } else {
            User user = new User(username.getText(), password.getText().toCharArray());
            System.out.println(user);
            System.exit(0);
        }
    }

    @FXML
    void mainMenu() {
        loadTemplate("file:src/main/java/gui/application.fxml");
    }
}

