package gui;

import javafx.fxml.FXML;

public class ApplicationController extends Controller {
    @FXML
    void exit() {
        System.out.println("exiting application");
        System.exit(0);
    }

    @FXML
    void login() {
        loadTemplate("file:src/main/java/gui/login.fxml");
    }

    @FXML
    void signUp() {
        loadTemplate("file:src/main/java/gui/signup.fxml");
    }
}
