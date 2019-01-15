package gui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class WarningController extends Controller {
    @FXML
    Label warning;
    TranslateTransition shake;

    @FXML
    void initialize() {
        shake = new TranslateTransition(new Duration(50), warning);
        shake.setFromX(0);
        shake.setByX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
    }

    protected void shakeError(String text) {
        warning.setText(text);
        warning.setTextFill(Color.RED);
        warning.setVisible(true);
        shake.playFromStart();
    }

    protected void displaySuccess(String text) {
        warning.setText(text);
        warning.setTextFill(Color.GREEN);
        warning.setVisible(true);
    }
}
