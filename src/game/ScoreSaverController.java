package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreSaverController implements Exitable{

    //TODO result mechanics
    @FXML
    Button saveResult;

    @FXML
    TextField playerName;


    //TODO communication with object - player
    public void saveResult() {
        new Player(playerName.getText(), 20);
    }

    @Override
    public void exitApp() {
        Stage stage = (Stage) saveResult.getScene().getWindow();
        stage.close();
    }
}
