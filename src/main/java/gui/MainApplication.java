package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import userManagement.UserManagerAdministrator;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    protected UserManagerAdministrator userAdmin;

    public static void main(String[] args) {
        launch(args);
    }

    public UserManagerAdministrator getUserAdmin() {
        return userAdmin;
    }

    protected void setUserAdmin(boolean isPersistent) {
        if (isPersistent) {
            try {
                userAdmin = new UserManagerAdministrator("users.ser");
            } catch (IOException e) {
                Alert storageError = new Alert(Alert.AlertType.ERROR);
                storageError.initStyle(StageStyle.UTILITY);
                storageError.setTitle("Persistent Storage");
                storageError.setHeaderText("Initialization error");
                storageError.setContentText("Could not initialize persistent Storage. " +
                        "Falling back to using in-memory storage");
                storageError.showAndWait();
                userAdmin = new UserManagerAdministrator();
            }
        } else {
            userAdmin = new UserManagerAdministrator();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUserAdmin(askStorage());
        initStage(primaryStage);
        primaryStage.show();
    }

    protected boolean askStorage() {
        Alert storageAlert = new Alert(Alert.AlertType.CONFIRMATION);
        storageAlert.initStyle(StageStyle.UTILITY);
        storageAlert.setTitle("Persistent Storage");
        storageAlert.setHeaderText("Initialize persistent storage");
        storageAlert.setContentText("Do you want to set up persistent storage?");
        storageAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        return storageAlert.showAndWait().filter(buttonType -> buttonType == ButtonType.YES).isPresent();
    }

    protected void initStage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(new URL("file:src/main/resources/application.fxml"));
        stage.setTitle("User Management");
        stage.setScene(loader.load());
        ((Controller) loader.getController()).setMainApplication(this);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setOnCloseRequest(event -> System.out.println("Exiting application"));
    }
}
