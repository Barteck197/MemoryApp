package game;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static game.BoardController.score;
import static game.MenuController.resultListArray;

public class ScoreSaverController implements Exitable {

    @FXML
    Button saveResult;

    @FXML
    TextField playerName;

    public void saveResult() throws IOException {

        if (playerName.getText().equals("")) {
            alertWrongInput();
        } else {
            Player pl = new Player(playerName.getText(), score);

            //adding player to global high score list
            resultListArray.add(pl);

            File resultFile = new File("highScores.txt");

            if (!resultFile.exists()) {
                resultFile.createNewFile();
            }

            FileOutputStream fs;
            ObjectOutputStream os = null;

            try {
                fs = new FileOutputStream(resultFile, true);
                os = new ObjectOutputStream(fs);
                os.writeObject(resultListArray);
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
            cleanUserInput();
            exitApp();
        }


    }

    public void cleanUserInput() {
        playerName.setText("");
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);

        al.setTitle("OK");
        al.setHeaderText("Dziękujemy, zapisano");
        al.show();
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
