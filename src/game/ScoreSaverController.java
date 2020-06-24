package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ScoreSaverController implements Exitable {

    //TODO result mechanics
    @FXML
    Button saveResult;

    @FXML
    TextField playerName;


    public void saveResult() throws IOException {
        Player pl = new Player(playerName.getText(), 20);

        //TODO add to new file instead of creating new one

        FileOutputStream fs;
        ObjectOutputStream os = null;

        try {
            fs = new FileOutputStream("highScores.txt", true);
            os = new ObjectOutputStream(fs);
            os.writeObject(pl);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
        }


    }

    @Override
    public void exitApp() {
        Stage stage = (Stage) saveResult.getScene().getWindow();
        stage.close();
    }
}
