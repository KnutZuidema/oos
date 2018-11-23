package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ApplicationController {
    @FXML
    Scene scene;

    @FXML
    void exit() {
        System.out.println("exiting application");
        System.exit(0);
    }

    @FXML
    void login() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:src/main/java/gui/login.fxml"));
            ((Stage) scene.getWindow()).setScene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUp(){
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:src/main/java/gui/signup.fxml"));
            ((Stage) scene.getWindow()).setScene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
