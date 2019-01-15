package remote;

import gui.MainApplication;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Client extends MainApplication {
    boolean isRemote;

    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> {
            try {
                new Server().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.setName("Server");
        Thread clientThread = new Thread(() -> launch(args));
        clientThread.setName("Client");
        serverThread.start();
        clientThread.start();
        try {
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUserAdmin(boolean isPersistent) {
        if (isRemote) {
            try {
                if (isPersistent) {
                    userAdmin = new RemoteUserAdmin("usersRemote");
                } else {
                    userAdmin = new RemoteUserAdmin();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                Alert storageError = new Alert(Alert.AlertType.ERROR);
                storageError.initStyle(StageStyle.UTILITY);
                storageError.setTitle("Remote Storage");
                storageError.setHeaderText("Initialization error");
                storageError.setContentText("Could not initialize remote Storage. " +
                        "Falling back to using local storage");
                storageError.showAndWait();
                isRemote = false;
                setUserAdmin(isPersistent);
            }
        } else {
            super.setUserAdmin(isPersistent);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        isRemote = askRemote();
        super.start(primaryStage);
    }

    protected boolean askRemote() {
        Alert storageAlert = new Alert(Alert.AlertType.CONFIRMATION);
        storageAlert.initStyle(StageStyle.UTILITY);
        storageAlert.setTitle("Remote Storage");
        storageAlert.setHeaderText("Initialize remote storage");
        storageAlert.setContentText("Do you want to set up remote storage?");
        storageAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        return storageAlert.showAndWait().filter(buttonType -> buttonType == ButtonType.YES).isPresent();
    }
}
