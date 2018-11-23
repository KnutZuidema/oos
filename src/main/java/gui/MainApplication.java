package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.net.URL;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new URL("file:src/main/java/gui/application.fxml"));
        primaryStage.setTitle("User Management");
        primaryStage.setScene(loader.load());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
