package gui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userManagement.User;
import userManagement.UserManagerAdministrator;

import java.io.IOException;

public class LoginController extends Controller {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    CheckBox newLogin;
    private boolean isNewLogin;
    UserManagerAdministrator userAdmin;

    @FXML
    void initialize() {
        newLogin.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            isNewLogin = newValue;
        }));
        try {
            userAdmin = new UserManagerAdministrator("users.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void login() {
        if (isNewLogin) {
            loadTemplate("file:src/main/java/gui/signup.fxml");
        } else {
            User user = new User(username.getText(), password.getText().toCharArray());
            System.out.println(user);
            try {
                if (userAdmin.verifyUser(user)) {
                    System.out.println("Logged in successfully");
                } else {
                    System.out.println("Invalid credentials");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void mainMenu() {
        loadTemplate("file:src/main/java/gui/application.fxml");
    }
}

