package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import userManagement.UserManagerAdministrator;

import java.net.URL;

public class MainApplication extends Application {

    protected UserManagerAdministrator userAdmin;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new URL("file:src/main/resources/application.fxml"));
        stage.setTitle("User Management");
        stage.setScene(loader.load());
        ((Controller) loader.getController()).setMainApplication(this);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setOnCloseRequest(event -> System.out.println("Exiting application"));
    }
}
