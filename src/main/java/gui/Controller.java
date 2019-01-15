package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

abstract public class Controller {
    @FXML
    Scene scene;
    protected MainApplication mainApplication;

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    void loadTemplate(String templateLocation){
        try {
            FXMLLoader loader = new FXMLLoader(new URL(templateLocation));
            ((Stage) scene.getWindow()).setScene(loader.load());
            ((Controller) loader.getController()).setMainApplication(mainApplication);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
