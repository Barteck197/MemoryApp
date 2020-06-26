package game;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ScoreSaverController implements Exitable {

    //TODO Highscores are kept in global observableList

    //TODO result mechanics
    private int playerResult;

    @FXML
    Button saveResult;

    @FXML
    TextField playerName;

    public void saveResult() throws IOException {

        if (playerName.getText().equals("")) {
            alertWrongInput();
        } else {
            Player pl = new Player(playerName.getText(), playerResult);

            FileOutputStream fs;
            ObjectOutputStream os = null;

            try {
                fs = new FileOutputStream("highScores.txt", true);
                os = new ObjectOutputStream(fs);
                os.writeObject(pl);
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }


    }

    public void setPlayerResult(int result){
        this.playerResult = result;
    }

    public void alertWrongInput() {
        Alert al = new Alert(Alert.AlertType.WARNING);

        al.setTitle("Błąd");
        al.setHeaderText("Podaj swoje imię");
        al.show();
    }

    @Override
    public void exitApp() {
        Stage stage = (Stage) saveResult.getScene().getWindow();
        stage.close();
    }
}
