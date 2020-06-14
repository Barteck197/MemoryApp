package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HighScoresController {
    @FXML
    Button backButton;

    @FXML
    private void backToMenu() throws IOException {
        Stage window;
        Scene menuScene;

        menuScene = backButton.getScene();

        window = (Stage) menuScene.getWindow();

        menuScene = new Scene(FXMLLoader.load(getClass().getResource("scenes/mainMenu.fxml")));
        window.setScene(menuScene);
        window.setWidth(600);
        window.setHeight(400);
    }
}
