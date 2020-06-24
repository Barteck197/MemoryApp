package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ScoreSaverController implements Exitable{

    //TODO result mechanics
    @FXML
    Button saveResult;

    @FXML
    TextField playerName;


    //TODO communication with object - player
    public void saveResult() throws IOException {
        Player pl = new Player(playerName.getText(), 20);

        FileOutputStream fs = new FileOutputStream("highScores.txt");
        ObjectOutputStream os = new ObjectOutputStream(fs);

        os.writeObject(pl);
        os.flush();
        os.close();
    }

    @Override
    public void exitApp() {
        Stage stage = (Stage) saveResult.getScene().getWindow();
        stage.close();
    }
}
