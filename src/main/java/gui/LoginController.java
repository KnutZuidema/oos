package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userManagement.User;

import java.net.URL;

public class LoginController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    CheckBox newLogin;
    @FXML
    Scene scene;
    private boolean isNewLogin;

    @FXML
    void newLoginChanged() {
        this.isNewLogin = newLogin.isSelected();
        System.out.println(isNewLogin);
    }

    @FXML
    void login() {
        if (isNewLogin) {
            try {
                FXMLLoader loader = new FXMLLoader(new URL("file:src/main/java/gui/signup.fxml"));
                ((Stage) scene.getWindow()).setScene(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            User user = new User(username.getText(), password.getText().toCharArray());
            System.out.println(user);
            System.exit(0);
        }
    }
}
