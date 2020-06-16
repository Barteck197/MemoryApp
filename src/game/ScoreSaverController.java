package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ScoreSaverController {

    @FXML
    Button saveResult;

    @FXML
    TextField playerName;


    //TODO communication with object - player
    public void saveResult() {
        new Player(playerName.getText(), 20);
    }
}
